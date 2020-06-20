package br.com.puppy8.devices;

public class Screen {

	static final int SCREEN_SIZE = 0x800;

	private byte[] pixelData;

	public Screen(int sSize) {
		pixelData = new byte[sSize];
	}

	public void writePixelValue(int position, int value){
		if (position < 0 || position >= pixelData.length) {
			clearScreen();
		}else
			pixelData[position] = (byte) value;
	}

	public byte readPixelValue(int position){
		if (position < 0 || position >= pixelData.length) {
			clearScreen();   
		}
		return pixelData[position];
	}

	public void clearScreen() {
		for (int i = 0; i < pixelData.length; i++) {
			pixelData[i] = 0;
		}
	}

	public byte[] getScreen() {
		return pixelData;
	}
}
