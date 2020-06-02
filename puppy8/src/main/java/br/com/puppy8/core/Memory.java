package br.com.puppy8.core;

import java.util.Arrays;

public class Memory {
	static final int SIZE_4096 = 0x1000;
	int[] memory;
	
	public Memory(int size) {
		this.memory = new int[size];
	}

	public int size() {
		return this.memory.length;
	}

	public int read(int position) {
		return memory[position];
	}

	public void write(int data, int position) {
		memory[position] = data;
	}
	
	public void printFullMemory() {
		System.out.print(Arrays.toString(memory));
	}
}
