package br.com.puppy8.utils;

import br.com.puppy8.core.Memory;

public class Program {
	private static final int MEMORY_OFFSET = 0x200;
	Memory memory;
	
	public Program(Memory memory) {
		this.memory = memory;
	}

	public void loadProgram() {
		
		int[] progTest = new int[] {0xF0, 0x90, 0x90, 0x90, 0xF0}; //0
		int address = MEMORY_OFFSET; 
		for (int i = 0; i < progTest.length; i++) {
			memory.write(progTest[i], address++);
		}
	}

}
