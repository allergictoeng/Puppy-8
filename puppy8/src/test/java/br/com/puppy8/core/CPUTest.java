package br.com.puppy8.core;

import static org.junit.Assert.assertTrue;

import br.com.puppy8.core.Stack;

import org.junit.Before;
import org.junit.Test;

public class CPUTest {
	
	private Memory memory;
	private CPU cpu;
	private Stack stack;
	
	@Before
	public void create() {
		memory = new Memory(Memory.SIZE_4096);
		stack = new Stack(Stack.STACK_SIZE16);
		cpu = new CPU(memory,stack);
	}
	
	@Test
	public void testWriteAndReadRegisters() {
		cpu.writeInRegister(CPU.REGISTER_V1, 0x3c);
		assertTrue(cpu.readInRegister(CPU.REGISTER_V1) == 60);
	}
	
	@Test
	public void testJumpsToAddressNNN() {
		cpu.decode(0x1EEE);
		assertTrue(cpu.getProgramCounter() == 0x0EEE);		
	}
	
}
