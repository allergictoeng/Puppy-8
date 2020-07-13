package br.com.puppy8.core;

import java.util.Arrays;

public class Memory {
	public static final int SIZE_4096 = 0x1000;
	int[] memory;
	
	public Memory(int size) {
		this.memory = new int[size];
	}

	public int size() {
		return this.memory.length;
	}

	public int read(int adress) {
		return memory[adress];
	}

	public void write(int data, int adress) {
			memory[adress] = data;
	}
	
	public void printFullMemory() {
		System.out.print(Arrays.toString(memory));
	}
}
