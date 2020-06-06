package br.com.puppy8.core;

import java.util.Arrays;

public class CPU {
	
	static final int REGISTERS_8BIT_SIZE16 = 0x10;
	
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
	private Stack stack;
	//the CHIP-8 interpreter itself occupies the first 512 bytes of the memory space on these machines.
	//For this reason, most programs written for the original system begin at memory location 512 (0x200)
	protected int programCounter;
	protected int stackPointer;
	protected int opcode;

	private int[] registers = new int[]
	{REGISTER_V0, REGISTER_V1, REGISTER_V2,
	 REGISTER_V3, REGISTER_V4, REGISTER_V5,
	 REGISTER_V6, REGISTER_V7, REGISTER_V8,
	 REGISTER_V9, REGISTER_VA, REGISTER_VB,
	 REGISTER_VC, REGISTER_VD, REGISTER_VE,
	 REGISTER_VF};
	
	public int readInRegister(int registerV) {
		return registers[registerV];
	}
	
	public void writeInRegister(int registerV, int data) {
		registers[registerV] = data;
	}
	
	public void printRegisters() {
		System.out.println(Arrays.toString(registers)); 
	}
	public void printFullMemory() {
		memory.printFullMemory();
	}
	
	public CPU(Memory memory, Stack stack) {
		this.stack = stack;
		this.memory = memory;
		this.programCounter = 0x200;
		this.stackPointer = 0;
		this.opcode = 0;
	}

	public void decode(int opcode) {
		
		this.opcode = opcode & 0xF000;
		
		switch(this.opcode) { // this operation get a first opcode position
			
		case 0x0000:
		{
			switch(opcode & 0x00FF) { //this operation get de last opcode position
				
				case 0x00E0: 
					clearScreen();
					break;
					
				case 0x00EE: 
					returnFromASubroutine();
					break;
				
				default:
					// wait a decision
			}
		}
		
		case 0x1000:
			jumpToAdress();
			break;
			
		case 0x2000:
			callsSubroutineAt();
			break;
			
		case 0x3000:
			skipsNextInstrIfVXEqualsNN();
			break;
			
		case 0x4000:
			skipsNextInstrIfVXDoesnEqualNN();
			break;
			
		case 0x5000:
			skipsNextInstrIfVXEqualsVY();
			break;
			
		case 0x6000:
			setsVXToNN();
			break;
			
		case 0x7000:
			addsNNToVX();
			break;
			
		case 0x8000:
		case 0x9000:
		case 0xA000:
		case 0xB000:
		case 0xC000:
		case 0xD000:
		case 0xE000:
		case 0xF000:
		
		
		
		}
		
	}

	private void addsNNToVX() {
		// TODO Auto-generated method stub
		
	}

	private void setsVXToNN() {
		// TODO Auto-generated method stub
		
	}

	private void skipsNextInstrIfVXEqualsVY() {
		// TODO Auto-generated method stub
		
	}

	private void skipsNextInstrIfVXDoesnEqualNN() {
		// TODO Auto-generated method stub
		
	}

	private void skipsNextInstrIfVXEqualsNN() {
		// TODO Auto-generated method stub
		
	}

	private void callsSubroutineAt() {
		// TODO Auto-generated method stub
		
	}
	
	private void jumpToAdress() {
		programCounter = (this.opcode & 0x0FFF);		
	}

	private void returnFromASubroutine() {
		// TODO Auto-generated method stub
		
	}

	private void clearScreen() {
		// TODO Auto-generated method stub
		
	}
}