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
		cpu.writeInRegister(CPU.REGISTER_V1, 0x3c);
		assertTrue(cpu.readInRegister(CPU.REGISTER_V1) == 0x3c);
	}
	
	@Test
	public void testJumpsToAddressNNN() {
		cpu.decode(0x1EEE);
		assertTrue(cpu.getProgramCounter() == 0xEEE);		
	}
	
	@Test
	public void testCallsSubroutineAt() {
		cpu.decode(0x2EEE);
		assertTrue(cpu.getStack().stackSize() == 1);
		assertTrue(cpu.getProgramCounter() == 0xEEE);
	}
}
