package br.com.puppy8.utils;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.puppy8.core.Memory;

public class Loader {
	private static final int MEMORY_OFFSET = 0x200;
	private Memory memory;
	private Program program;

	public Loader(Program program, Memory memory) {
		this.program = program;
		this.memory = memory;
		load();
	}

	public void load(){
		DataInputStream inputStream = this.program.dataStream();
		int address = MEMORY_OFFSET;

		try {
			while (inputStream.available() > 0) {
				int value = inputStream.readByte();
				memory.write(address, value);
				address++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}