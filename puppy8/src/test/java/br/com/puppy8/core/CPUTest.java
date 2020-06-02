package br.com.puppy8.core;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CPUTest {
	
	private Memory memory;
	private CPU cpu;
	
	@Before
	public void create() {
		memory = new Memory(Memory.SIZE_4096);
		cpu = new CPU(memory);
	}
	
	@Test
	public void testWriteAndReadRegisters() {
		cpu.writeRegister(CPU.REGISTER_V1, 0x3c);
		assertTrue(cpu.readRegister(CPU.REGISTER_V1) == 60);
	}
	
	
}
