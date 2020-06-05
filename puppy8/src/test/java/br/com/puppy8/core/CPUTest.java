package br.com.puppy8.core;

import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class CPUTest {
	
	private Memory memory;
	private CPU cpu;
	
	@Before
	public void create() {
		memory = new Memory(Memory.SIZE_4096);
		//cpu = new CPU(memory,new Stack(0x00));
	}
	
	@Test
	public void testWriteAndReadRegisters() {
		cpu.writeInRegister(CPU.REGISTER_V1, 0x3c);
		assertTrue(cpu.readInRegister(CPU.REGISTER_V1) == 60);
	}
	
	@Test
	public void testJumpsToAddressNNN() {
		cpu.programCounter = 0;
		//cpu.decode(cpu.programCounter);
	}
	
}
