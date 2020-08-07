package br.com.puppy8.utils;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.puppy8.core.Memory;

public class Program {
	private static final int MEMORY_OFFSET = 0x200;
	Memory memory;
	
	public Program(Memory memory) {
		this.memory = memory;
	}

	public void loadProgram(String fileName){
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DataInputStream inputStream = new DataInputStream(fileInputStream);

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
