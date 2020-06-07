package br.com.puppy8.core;

import java.util.Arrays;

public class CPU {

	private static final int REGISTERS_8BIT_SIZE16 = 0x10;

	private static final int OP_00E0 = 0x00E0;
	private static final int OP_00EE = 0x00EE;
	private static final int OP_1NNN = 0x1000;
	private static final int OP_2NNN = 0x2000;
	private static final int OP_3XNN = 0x3000;
	private static final int OP_4XNN = 0x4000;
	private static final int OP_5XY0 = 0x5000;
	private static final int OP_6XNN = 0x6000;
	private static final int OP_7XNN = 0x7000;

	private static final int OP_8XY0 = 0x0000;
	private static final int OP_8XY1 = 0x0001;
	private static final int OP_8XY2 = 0x0002;
	private static final int OP_8XY3 = 0x0003;
	private static final int OP_8XY4 = 0x0004;
	private static final int OP_8XY5 = 0x0005;
	private static final int OP_8XY6 = 0x0006;
	private static final int OP_8XY7 = 0x0007;
	private static final int OP_8XYE = 0x000E;

	private static final int OP_9XY0 = 0x9000;
	private static final int OP_ANNN = 0xA000;
	private static final int OP_BNNN = 0xB000;
	private static final int OP_CXNN = 0xC000;
	private static final int OP_DXYN = 0xD000;

	private static final int OP_EX9E = 0x009E;
	private static final int OP_EXA1 = 0x00A1;

	private static final int OP_FX07 = 0x0007;
	private static final int OP_FX0A = 0x000A;
	private static final int OP_FX15 = 0x0015;
	private static final int OP_FX18 = 0x0018;
	private static final int OP_FX1E = 0x001E;
	private static final int OP_FX29 = 0x0029;
	private static final int OP_FX33 = 0x0033;
	private static final int OP_FX55 = 0x0055;
	private static final int OP_FX65 = 0x0065;
	
	private Memory memory;
	private Stack stack;
	private int programCounter;

	protected int stackPointer;
	private int opcode;
	private int[] registers;

	public int getProgramCounter() {
		return programCounter;
	}
	
	public Stack getStack() {
		return stack;
	}

	public int readInRegister(int registerV) {
		return registers[registerV];
	}

	public void writeInRegister(int registerV, int data) {
		registers[registerV] = data;
	}
	
	public void printFullMemory() {
		memory.printFullMemory();
	}

	public CPU(Memory memory) {
		this.memory = memory;
		this.programCounter = 0x200;
		this.stackPointer = 0;
		this.stack = new Stack(Stack.STACK_SIZE16);
		this.registers = new int[REGISTERS_8BIT_SIZE16];
	}

	public void decode(int opcode) {

		this.opcode = opcode;

		switch(this.opcode & 0xF000) { 

		case 0x0000:{

			switch(this.opcode & 0x00FF) {

			case OP_00E0: clearScreen();	break;

			case OP_00EE: returnFromASubroutine(); break;

			}
		}

		case OP_1NNN: jumpToAdress(); break;

		case OP_2NNN: callsSubroutineAt(); break;

		case OP_3XNN: skipsNextInstrIfVXEqualsNN(); break;

		case OP_4XNN: skipsNextInstrIfVXDoesnEqualNN(); break;

		case OP_5XY0: skipsNextInstrIfVXEqualsVY(); break;

		case OP_6XNN: setsVXToNN(); break;

		case OP_7XNN: addsNNToVX(); break;

		case 0x8000:{

			switch(this.opcode & 0x000F) {

			case OP_8XY0: setsVXToTheValueOfVY(); break;

			case OP_8XY1: bitwiseOR(); break;

			case OP_8XY2: bitwiseAND(); break;

			case OP_8XY3: bitwiseXOR(); break;

			case OP_8XY4: addsVYToVX(); break;

			case OP_8XY5: subtracVYFromVX(); break;

			case OP_8XY6: shiftsVXToTheRightBy1(); break;

			case OP_8XY7: setsVXToVYMinusVX(); break;

			case OP_8XYE: shiftsVXtoTheLeftBy1(); break;

			}
		}

		case OP_9XY0: skipsNextInstructionIfVXdoesnEqualVY(); break;

		case OP_ANNN: setsItoAddressNNN(); break;

		case OP_BNNN: jumpsAddressNNNPlusV0(); break;

		case OP_CXNN: setsVXResultOfABitwiseAndOperationOnARandomNumber(); break;

		case OP_DXYN: drawsASprite(); break;

		case 0xE000:{

			switch(this.opcode & 0x00FF) {

			case OP_EX9E: skipsTheNextInstructionIfTheKeyStoredInVXIsPressed(); break;

			case OP_EXA1: skipsTheNextInstructionIfTheKeyStoredInVXisnPressed(); break;

			}
		}
		case 0xF000:{

			switch(this.opcode & 0x00FF) {

			case OP_FX07: setsVXToTheValueOfTheDelayTimer(); break;

			case OP_FX0A: keyPressIsAwaitedAndThenStoredInVX(); break;

			case OP_FX15: setsTheDelayTimerToVX(); break;

			case OP_FX18: setsTheSoundTimerToVX(); break;

			case OP_FX1E: addsVXToI(); break;

			case OP_FX29: setsIToTheLocationOfTheSpriteForTheCharacterInVX(); break;

			case OP_FX33: storesTheBinaryCodedDecimalRepresentationOfVX(); break;

			case OP_FX55: storesV0ToVXInMemoryStartingAtAddressI(); break;

			case OP_FX65: fillsV0ToVXWithValuesFromMemoryStartingAtAddressI(); break;

			}	
		}
		}
	}

	private void fillsV0ToVXWithValuesFromMemoryStartingAtAddressI() {
		// TODO Auto-generated method stub
		
	}

	private void storesV0ToVXInMemoryStartingAtAddressI() {
		// TODO Auto-generated method stub
		
	}

	private void storesTheBinaryCodedDecimalRepresentationOfVX() {
		// TODO Auto-generated method stub
		
	}

	private void setsIToTheLocationOfTheSpriteForTheCharacterInVX() {
		// TODO Auto-generated method stub
		
	}

	private void addsVXToI() {
		// TODO Auto-generated method stub
		
	}

	private void setsTheSoundTimerToVX() {
		// TODO Auto-generated method stub
		
	}

	private void setsTheDelayTimerToVX() {
		// TODO Auto-generated method stub
		
	}

	private void keyPressIsAwaitedAndThenStoredInVX() {
		// TODO Auto-generated method stub
		
	}

	private void setsVXToTheValueOfTheDelayTimer() {
		// TODO Auto-generated method stub
		
	}

	private void skipsTheNextInstructionIfTheKeyStoredInVXisnPressed() {
		// TODO Auto-generated method stub

	}

	private void skipsTheNextInstructionIfTheKeyStoredInVXIsPressed() {
		// TODO Auto-generated method stub

	}

	private void drawsASprite() {
		// TODO Auto-generated method stub

	}

	private void setsItoAddressNNN() {
		// TODO Auto-generated method stub

	}

	private void jumpsAddressNNNPlusV0() {
		// TODO Auto-generated method stub

	}

	private void setsVXResultOfABitwiseAndOperationOnARandomNumber() {
		// TODO Auto-generated method stub

	}

	private void skipsNextInstructionIfVXdoesnEqualVY() {
		// TODO Auto-generated method stub

	}

	private void shiftsVXtoTheLeftBy1() {
		// TODO Auto-generated method stub

	}

	private void setsVXToVYMinusVX() {
		// TODO Auto-generated method stub

	}

	private void shiftsVXToTheRightBy1() {
		// TODO Auto-generated method stub

	}

	private void subtracVYFromVX() {
		// TODO Auto-generated method stub

	}

	private void addsVYToVX() {
		// TODO Auto-generated method stub

	}

	private void bitwiseXOR() {
		// TODO Auto-generated method stub

	}

	private void bitwiseAND() {
		// TODO Auto-generated method stub

	}

	private void bitwiseOR() {
		// TODO Auto-generated method stub

	}

	private void setsVXToTheValueOfVY() {
		// TODO Auto-generated method stub

	}

	private void addsNNToVX() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;
		int registerData = (this.opcode & 0x00FF);
		int mergedValues = (readInRegister(registerPosition) + registerData) & 0xFF;
		writeInRegister(registerPosition, mergedValues);
		programCounter += 2; //Increment by 2
	}

	private void setsVXToNN() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;
		int registerData = (this.opcode & 0x00FF);
		writeInRegister(registerPosition, registerData);
		programCounter += 2; //Increment by 2
	}

	private void skipsNextInstrIfVXEqualsVY() {
		int registerXPosition = (this.opcode & 0x0F00) >> 8;
		int registerYPosition = (this.opcode & 0x00F0) >> 4;
		int registerXResult = readInRegister(registerXPosition);
		int registerYResult = readInRegister(registerYPosition);
		this.programCounter += (registerXResult == registerYResult)? 4 : 2; // Increment by 2 or 4	
	}

	private void skipsNextInstrIfVXDoesnEqualNN() {
		int registerPosition = (this.opcode & 0x0F00) >>  8;
		int registerData = (this.opcode & 0x00FF);
		int registerResult = readInRegister(registerPosition);
		this.programCounter += (registerResult != registerData)? 4 : 2; // Increment by 2 or 4
	}

	private void skipsNextInstrIfVXEqualsNN() {
		int registerPosition = (this.opcode & 0x0F00) >>  8;
		int registerData = (this.opcode & 0x00FF);
		int registerResult = readInRegister(registerPosition);
		this.programCounter += (registerResult == registerData)? 4 : 2; // Increment by 2 or 4
	}

	private void callsSubroutineAt() {
		this.stack.push(this.programCounter);
		this.programCounter = this.opcode & 0x0FFF;
	}

	private void jumpToAdress() {
		this.programCounter = this.opcode & 0x0FFF;
	}

	private void returnFromASubroutine() {
		// TODO Auto-generated method stub

	}

	private void clearScreen() {
		// TODO Auto-generated method stub

	}
}