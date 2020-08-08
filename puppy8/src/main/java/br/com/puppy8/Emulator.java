package br.com.puppy8;

import br.com.puppy8.core.CPU;
import br.com.puppy8.core.FontSet;
import br.com.puppy8.core.Memory;
import br.com.puppy8.peripherals.Frame;
import br.com.puppy8.peripherals.HexadecimaKeypad;
import br.com.puppy8.peripherals.Panel;
import br.com.puppy8.peripherals.Peripherals;
import br.com.puppy8.peripherals.Screen;
import br.com.puppy8.peripherals.Sound;
import br.com.puppy8.utils.Loader;
import br.com.puppy8.utils.Program;

public class Emulator extends Thread implements Peripherals{
	
	private CPU cpu;
	private boolean running;
	private Frame frame;
	private HexadecimaKeypad hexadecimaKeypad;
	private Panel panel;
	private Screen screen;
	private Sound sound;
	private Memory memory;
	private FontSet fontSet;
	
	public Emulator(Frame frame, Program program) {
		this.frame = frame;
		this.memory = new Memory(Memory.SIZE_4096);

		this.screen = new Screen(Screen.SCREEN_SIZE);
		this.hexadecimaKeypad = new HexadecimaKeypad();
		this.panel = new Panel(screen.getScreen());
		this.frame.setup(panel,hexadecimaKeypad);
		
		this.sound = new Sound();
		this.cpu = new CPU(memory, this);
		this.fontSet = new FontSet(memory);
		this.fontSet.loadFontSet();

		new Loader(program, memory);
	}
		
	@Override
	public void run() {
		this.running = true;
		while(running) {
			try {
				cpu.fetchDecodeExecuteCycle();				
				try {
					sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	public void stopEmulation() {
		this.running = false;
		clearScreen();
		repaintScreen();
		stopSound();
	}
	
	@Override
	public void repaintScreen() {	
		this.frame.repaint();
	}
	
	@Override
	public void clearScreen() {		
		this.screen.clearScreen();
	}
	
	@Override
	public int readPixelValue(int indexLocal) {		
		return screen.readPixelValue(indexLocal);
	}
	
	@Override
	public void writePixelValue(int position, int value) {
		this.screen.writePixelValue(position, value);		
	}
	
	@Override
	public void playSound() {
		this.sound.play();
	}
	
	@Override
	public void stopSound() {	
		this.sound.stop();
	}
	
	@Override
	public int getKeyPressed() {
		return hexadecimaKeypad.getKeyPressed();
	}
		
}