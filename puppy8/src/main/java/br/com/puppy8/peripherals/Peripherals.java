package br.com.puppy8.peripherals;

public interface Peripherals {
	
	public void repaintScreen();

	public void clearScreen();
	
	public int readPixelValue(int indexLocal);
	
	public void writePixelValue(int indexLocal, int i);

	public void playSound();

	public void stopSound();

	public int getKeyPressed();

}
