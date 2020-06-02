package br.com.puppy8.core;

import java.util.Arrays;

public class CPU {
	
	static final int REGISTERS_8BIT_SIZE16 = 0x10;
	static final int STACK_SIZE16 = 0x10;
	
	static final int REGISTER_V0  = 0x0 & 0xFF;
	static final int REGISTER_V1  = 0x1 & 0xFF;
	static final int REGISTER_V2  = 0x2 & 0xFF;
	static final int REGISTER_V3  = 0x3 & 0xFF;
	static final int REGISTER_V4  = 0x4 & 0xFF;
	static final int REGISTER_V5  = 0x5 & 0xFF;
	static final int REGISTER_V6  = 0x6 & 0xFF;
	static final int REGISTER_V7  = 0x7 & 0xFF;
	static final int REGISTER_V8  = 0x8 & 0xFF;
	static final int REGISTER_V9  = 0x9 & 0xFF;
	static final int REGISTER_VA  = 0xa & 0xFF;
	static final int REGISTER_VB  = 0xb & 0xFF;
	static final int REGISTER_VC  = 0xc & 0xFF;
	static final int REGISTER_VD  = 0xd & 0xFF;
	static final int REGISTER_VE  = 0xe & 0xFF;
	static final int REGISTER_VF  = 0xf & 0xFF;
	
	private Memory memory;
	private int[] stack = new int[STACK_SIZE16]; 
	
	//the CHIP-8 interpreter itself occupies the first 512 bytes of the memory space on these machines.
	//For this reason, most programs written for the original system begin at memory location 512 (0x200)
	protected int programCounter = 0x200;
	protected int stackPointer = 0;

	private int[] registers = new int[]
	{REGISTER_V0, REGISTER_V1, REGISTER_V2,
	 REGISTER_V3, REGISTER_V4, REGISTER_V5,
	 REGISTER_V6, REGISTER_V7, REGISTER_V8,
	 REGISTER_V9, REGISTER_VA, REGISTER_VB,
	 REGISTER_VC, REGISTER_VD, REGISTER_VE,
	 REGISTER_VF};
	
	public int readRegister(int registerV) {
		return registers[registerV];
	}
	
	public void writeRegister(int registerV, int data) {
		registers[registerV] = data;
	}
	
	public void printRegisters() {
		System.out.println(Arrays.toString(registers)); 
	}
	public void printFullMemory() {
		memory.printFullMemory();
	}
	
	public CPU(Memory memory) {
		this.memory = memory;
	}

}