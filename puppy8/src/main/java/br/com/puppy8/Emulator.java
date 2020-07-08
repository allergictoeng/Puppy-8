package br.com.puppy8;

import br.com.puppy8.peripherals.Peripherals;

public class Emulator extends Thread implements Peripherals{

	@Override
	public void repaintScreen() {		
	}

	@Override
	public void clearScreen() {		
	}

	@Override
	public int readPixelValue(int indexLocal) {		
		return 0;
	}

	@Override
	public void writePixelValue(int indexLocal, int i) {
	}

	@Override
	public void playSound() {
	}

	@Override
	public void stopSound() {	
	}

	@Override
	public int getKeyPressed() {
		return 0;
	}
	
	
	
	public Emulator(Arguments arguments) {
		// TODO Auto-generated constructor stub
	}

	public void emulate() {
		// TODO Auto-generated method stub
		
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
