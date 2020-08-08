package br.com.puppy8.peripherals;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HexadecimaKeypad extends KeyAdapter {
	
	private static final int[] HEXA_KEYS = {
			KeyEvent.VK_1, 
			KeyEvent.VK_2, 
			KeyEvent.VK_3, 
			KeyEvent.VK_4, 
			KeyEvent.VK_Q, 
			KeyEvent.VK_W, 
			KeyEvent.VK_E, 
			KeyEvent.VK_R, 
			KeyEvent.VK_A, 
			KeyEvent.VK_S, 
			KeyEvent.VK_D, 
			KeyEvent.VK_F, 
			KeyEvent.VK_Z, 
			KeyEvent.VK_X, 
			KeyEvent.VK_C, 
	};

	private int pressed;
	
	public HexadecimaKeypad() {
		this.pressed = 0x0;
	}
	
	@Override
	public void keyPressed(KeyEvent keyEvent) {
		this.pressed = key(keyEvent.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent keyEvent) {
		this.pressed = 0x0;
	}
	
	public int getKeyPressed() {
		return this.pressed;
	}
	
	private int key(int key) {
		for(int i = 0; i < HEXA_KEYS.length; i++) {
			if(HEXA_KEYS[i] == key) {
				return i + 1;
			}
		}
		return 0x0;
	}
	
}
