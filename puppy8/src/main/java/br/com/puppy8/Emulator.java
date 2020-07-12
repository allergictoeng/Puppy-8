package br.com.puppy8;

import br.com.puppy8.core.CPU;
import br.com.puppy8.peripherals.Frame;
import br.com.puppy8.peripherals.HexadecimaKeypad;
import br.com.puppy8.peripherals.Panel;
import br.com.puppy8.peripherals.Peripherals;
import br.com.puppy8.peripherals.Screen;
import br.com.puppy8.peripherals.Sound;

public class Emulator extends Thread implements Peripherals{
	
	private CPU cpu;
	private boolean running;
	private Frame frame;
	private HexadecimaKeypad hexadecimaKeypad;
	private Panel panel;
	private Screen screen;
	private Sound sound;
		
	public Emulator(Arguments arguments) {
		
	}

	public void emulate() {
		
	}
	
	@Override
	public void run() {
		this.running = true;
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
	
	public void stopEmulation() {
		this.running = false;
		clearScreen();
		repaintScreen();
		stopSound();
	}
	
	@Override
	public void repaintScreen() {	
		frame.repaint();
	}
	
	@Override
	public void clearScreen() {		
		screen.clearScreen();
	}
	
	@Override
	public int readPixelValue(int indexLocal) {		
		return screen.readPixelValue(indexLocal);
	}
	
	@Override
	public void writePixelValue(int position, int value) {
		screen.writePixelValue(position, value);
	}
	
	@Override
	public void playSound() {
		sound.play();
	}
	
	@Override
	public void stopSound() {	
		sound.stop();
	}
	
	@Override
	public int getKeyPressed() {
		return hexadecimaKeypad.getKeyPressed();
	}
	
	//Sound sound = new Sound();
	//sound.play();
	//sound.stop();
	//Screen scr = new Screen(0x800);
	//scr.writePixelValue(0, 1111);
	//Frame frame = new Frame();
	//Panel panel = new Panel(scr.getScreen());
	//frame.setup(panel);
	//frame.repaint();
	
}