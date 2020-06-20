package br.com.puppy8.devices;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestScreen {
	
	Screen screen;
	
	@Before
	public void init() {
		screen = new Screen(Screen.SCREEN_SIZE);
	}
	
	@Test
	public void testWriteAndReadPixel() {
		screen.writePixelValue(100, 0x2);
		assertTrue(screen.readPixelValue(100) == 0x2);
	}
	
	@Test
	public void testScreenSize() {
		assertTrue(screen.getScreen().length == Screen.SCREEN_SIZE);
	}
	
	@Test
	public void testClearScreen() {
		screen.writePixelValue(100, 0x2);
		screen.clearScreen();
		assertTrue(screen.readPixelValue(100) == 0x0);
	}
	
}
