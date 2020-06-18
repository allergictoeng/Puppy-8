package br.com.puppy8.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestMemory {
	
	private Memory memory;
	
	@Before
	public void create() {
		memory = new Memory(Memory.SIZE_4096);
	}
	
	@Test
	public void checkMemorySize() {
		assertTrue((memory.size() == 4096));
	}
	
	@Test
	public void readInMemory() {
		assertTrue(memory.read(0x0000) == 0);
	}
	
	@Test
	public void writeInMemory() {
		memory.write(0x000A,0x0000);
		assertTrue((memory.read(0x0000) == 0x000A));
		assertFalse((memory.read(0x0001) == 0x000A));
	}
	
}
