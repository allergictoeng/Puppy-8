package br.com.puppy8.peripherals;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HexadecimaKeypad extends KeyAdapter {
	
	private static final int[] HEXA_KEYS = {
			KeyEvent.VK_1, // 1
			KeyEvent.VK_2, // 2
			KeyEvent.VK_3, // 3
			KeyEvent.VK_Q, // 4
			KeyEvent.VK_W, // 5
			KeyEvent.VK_E, // 6
			KeyEvent.VK_A, // 7
			KeyEvent.VK_S, // 8
			KeyEvent.VK_D, // 9
			KeyEvent.VK_Z, // A
			KeyEvent.VK_C, // B
			KeyEvent.VK_4, // C
			KeyEvent.VK_R, // D
			KeyEvent.VK_F, // E
			KeyEvent.VK_V, // F
	};

	private int pressed = 0x0;
	
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
