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
		cpu.writeInRegister(0x01, 0x3c);
		assertTrue(cpu.readInRegister(0x01) == 0x3c);
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

	@Test
	public void testSkipsNextInstrIfVXEqualsNN() {
		cpu.decode(0x3127);
		assertTrue(cpu.getProgramCounter() == 0x202);
		
		cpu.decode(0x3230);
		assertTrue(cpu.getProgramCounter() == 0x204);
		
		cpu.decode(0x3055);
		assertTrue(cpu.getProgramCounter() == 0x206);
	}
	
	@Test
	public void testSkipsNextInstrIfVXDoesnEqualNN() {
		cpu.decode(0x4127);
		assertTrue(cpu.getProgramCounter() != 0x202);
		
		cpu.decode(0x4230);
		assertTrue(cpu.getProgramCounter() != 0x204);
		
		cpu.decode(0x4055);
		assertTrue(cpu.getProgramCounter() != 0x206);
	}
	
	@Test
	public void testSkipsNextInstrIfVXEqualsVY() {
		cpu.writeInRegister(0, 0x30);
		cpu.writeInRegister(1, 0x30);
		cpu.decode(0x5010);
		assertTrue(cpu.getProgramCounter() == 0x204);
		
		cpu.writeInRegister(0, 0x41);
		cpu.writeInRegister(1, 0x20);
		cpu.decode(0x5010);
		assertTrue(cpu.getProgramCounter() == 0x206);
	}
}
