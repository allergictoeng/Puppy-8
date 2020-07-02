package br.com.puppy8.core;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.puppy8.devices.Screen;

public class TestCPU {
	
	private Memory memory;
	private CPU cpu;
	private Screen screen;
	
	@Before
	public void create() {
		memory = new Memory(Memory.SIZE_4096);
		screen = new Screen(Screen.SCREEN_SIZE);
		cpu = new CPU(memory, screen);
	}
	
	@Test
	public void testClearScreen() {
		screen.writePixelValue(100, 0x2);
		cpu.decode(0x00E0);
		assertTrue(screen.readPixelValue(100) == 0x0);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test
	public void testReturnFromASubroutine() {
		cpu.decode(0x2EEE);
		assertTrue(cpu.getProgramCounter() == 0xEEE);
		cpu.decode(0x00EE);
		assertTrue(cpu.getProgramCounter() == 0x202);
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
	
	@Test
	public void testSetsVXToNN() {
		cpu.decode(0x6110);
		assertTrue(cpu.readInRegister(1) == 0x10);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test
	public void testAddsNNToVX() {
		cpu.writeInRegister(1, 0x10);
		cpu.decode(0x7110);
		assertTrue(cpu.readInRegister(1) == 0x20);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test
	public void testSetsVXToTheValueOfVY() {
		cpu.writeInRegister(2, 0x10);
		cpu.decode(0x8120);
		assertTrue(cpu.readInRegister(1) == 0x10);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test
	public void testBitwiseOR() {
		cpu.writeInRegister(1, 0x10);
		cpu.writeInRegister(2, 0x11);
		cpu.decode(0x8121);
		assertTrue(cpu.readInRegister(1) == 0x11);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test
	public void testBitwiseAND() {
		cpu.writeInRegister(1, 0x10);
		cpu.writeInRegister(2, 0x11);
		cpu.decode(0x8122);
		assertTrue(cpu.readInRegister(1) == 0x10);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test
	public void testBitwiseXOR() {
		cpu.writeInRegister(1, 0x10);
		cpu.writeInRegister(2, 0x11);
		cpu.decode(0x8123);
		assertTrue(cpu.readInRegister(1) == 0x1);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test
	public void testAddsVYToVXWithCarry() {
		cpu.writeInRegister(1, 0x10);
		cpu.writeInRegister(2, 0x11);
		cpu.decode(0x8124);
		assertTrue(cpu.readInRegister(0x1) == 0x21);
		assertTrue(cpu.readInRegister(0xF) == 0x1);
		assertTrue(cpu.getProgramCounter() == 0x202);		
	}
	
	@Test
	public void testAddsVYToVXWithoutCarry() {
		cpu.writeInRegister(1, 0x5);
		cpu.writeInRegister(2, 0x7);
		cpu.decode(0x8124);
		assertTrue(cpu.readInRegister(0x1) == 0xC);
		assertTrue(cpu.readInRegister(0xF) == 0x0);
		assertTrue(cpu.getProgramCounter() == 0x202);		
	}
	
	@Test
	public void testSubtracVYFromVXTheresABorrow() {
		cpu.writeInRegister(1, 0x5);
		cpu.writeInRegister(2, 0xA);
		cpu.decode(0x8125);
		assertTrue(cpu.readInRegister(0x1) == 0xFB);
		assertTrue(cpu.readInRegister(0xF) == 0x0);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test
	public void testSubtracVYFromVXIsntABorrow() {
		cpu.writeInRegister(1, 0xA);
		cpu.writeInRegister(2, 0x5);
		cpu.decode(0x8125);
		assertTrue(cpu.readInRegister(0x1) == 0x5);
		assertTrue(cpu.readInRegister(0xF) == 0x1);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}

	@Test
	public void testShiftsVXToTheRightBy1() {
		cpu.writeInRegister(1, 0x17);
		cpu.decode(0x8106);
		assertTrue(cpu.readInRegister(0x1) == 0xB);
		assertTrue(cpu.readInRegister(0xF) == 0x0);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test
	public void testSetsVXToVYMinusVXTheresABorrow(){
		cpu.writeInRegister(1, 0x5);
		cpu.writeInRegister(2, 0xF);
		cpu.decode(0x8127);
		assertTrue(cpu.readInRegister(0x1) == 0xA);
		assertTrue(cpu.readInRegister(0xF) == 0x1);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test
	public void testSetsVXToVYMinusVXIsntABorrow(){
		cpu.writeInRegister(1, 0xF);
		cpu.writeInRegister(2, 0xA);
		cpu.decode(0x8127);
		assertTrue(cpu.readInRegister(0x1) == 0xFB);
		assertTrue(cpu.readInRegister(0xF) == 0x0);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}

	@Test
	public void testShiftsVXtoTheLeftBy1() {
		cpu.writeInRegister(1, 0x17);
		cpu.decode(0x810E);
		assertTrue(cpu.readInRegister(0x1) == 0x2E);
		assertTrue(cpu.readInRegister(0xF) == 0x0);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test 
	public void testSkipsNextInstructionIfVXdoesnEqualVY() {
		cpu.writeInRegister(1, 0x10);
		cpu.writeInRegister(2, 0x11);
		cpu.decode(0x9120);
		assertTrue(cpu.getProgramCounter() == 0x204);
	}
	
	@Test
	public void testSetsItoAddressNNN() {
		cpu.decode(0xAFF0);
		assertTrue(cpu.getIndex() == 0xFF0);
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test
	public void testJumpsAddressNNNPlusV0() {
		cpu.decode(0xA010);
		cpu.decode(0xB110);
		assertTrue(cpu.getIndex() == 0x010);
		assertTrue(cpu.getProgramCounter() == 0x120);
	}
	
	@Test
	public void testSetsVXResultOfABitwiseAndOperationOnARandomNumber() {
		cpu.decode(0xC111);
		int register = cpu.readInRegister(0x1);
		assertTrue((register >= 0x000) && (register <= 0x100));
		assertTrue(cpu.getProgramCounter() == 0x202);
	}
	
	@Test
	public void testSetsVXToTheValueOfTheDelayTimer() {
		// [TODO]: don`t forget this guy
	}
	
	@Test
	public void testDrawsASprite() {
	//[TODO]: Repaint screen function here!!, don`t forget //
	// Draw number 1 =	0x20, 0x60, 0x20 0x20, 0x70
	}

	@Test
	public void testSetsTheDelayTimerToVX() {
		cpu.decode(0xF115);
		// [TODO]: don`t forget this guy
	}

	@Test
	public void testSetsTheSoundTimerToVX(){
		cpu.decode(0xF218);
		// [TODO]: don`t forget this guy
	}
	
	
	
}
