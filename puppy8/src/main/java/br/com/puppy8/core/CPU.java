package br.com.puppy8.core;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import br.com.puppy8.peripherals.Peripherals;

public class CPU {

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

	private static final int REGISTERS_8BIT_SIZE16 = 0x10;

	private Peripherals peripherals;
	private Memory memory;
	private Stack stack;
	private int programCounter;

	private int opcode;
	private int index;
	private int soundTimers;
	private int delay;
	private long nextTimer;
	private int [] registers;	

	public int getProgramCounter() {
		return programCounter;
	}

	public Stack getStack() {
		return stack;
	}

	public int readInRegister(int registerV) {
		return (registers[registerV] & 0xFF);
	}

	public void writeInRegister(int registerV, int data) {
		registers[registerV] = (data & 0xFF);
	}

	public void printRegisters() {
		System.out.println(Arrays.toString(registers));
	}

	public void printPC() {
		System.out.println("pc: "+this.programCounter);
	}

	public void printIndex() {
		System.out.println("Index: "+this.index);
	}

	public void printStack() {
		this.stack.print();
	}

	public int getIndex() {
		return this.index;
	}

	public void printFullMemory() {
		memory.printFullMemory();
	}

	public CPU(Memory memory, Peripherals peripherals) {
		this.memory = memory;
		this.programCounter = 0x200;
		this.peripherals = peripherals;
		this.stack = new Stack(Stack.STACK_SIZE16);
		this.registers = new int[REGISTERS_8BIT_SIZE16];
		this.index = 0;
		this.delay = 0;
		this.soundTimers = 0;
		this.nextTimer = 0;		
	}


	public void fetchDecodeExecuteCycle() {
		int adress = this.programCounter;
		int instruction = ((memory.read(adress) << 8) | memory.read(adress + 1));

		long time = System.currentTimeMillis();

		if(time > this.nextTimer ) {
			decrementTimers();
			this.nextTimer = time + (1000/60);
		}
		decode(instruction);		
	}

	private void decrementTimers() {
		if (delay > 0) {
			delay--;
		}

		if (soundTimers > 0) {
			this.peripherals.playSound();
			soundTimers--;
		}

		if (soundTimers == 0) {
			this.peripherals.stopSound();
		}
	}

	public void decode(int opcode) {

		this.opcode = opcode;

		switch(this.opcode & 0xF000) { 

		case 0x0000:{

			switch(this.opcode & 0x00FF) {

			case OP_00E0: clearScreen();	break;

			case OP_00EE: returnFromASubroutine(); break;

			} break;
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

			} break;
		}

		case OP_9XY0: skipsNextInstructionIfVXdoesnEqualVY(); break;

		case OP_ANNN: setsItoAddressNNN(); break;

		case OP_BNNN: jumpsAddressNNNPlusV0(); break;

		case OP_CXNN: setsVXResultOfABitwiseAndOperationOnARandomNumber(); break;

		case OP_DXYN: drawsASprite(); break;

		case 0xE000:{

			switch(this.opcode & 0x00FF) {

			case OP_EX9E: skipsTheNextInstructionIfTheKeyStoredInVXIsPressed(); break;

			case OP_EXA1: skipsTheNextInstructionIfTheKeyStoredInVXisNPressed(); break;

			} break;
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

			} break;
		}
		}
	}

	private void clearScreen() {
		this.peripherals.clearScreen();
		this.programCounter += 2;
	}

	private void returnFromASubroutine() {
		this.programCounter = (this.stack.pop() + 2);
	}

	private void jumpToAdress() {
		this.programCounter = this.opcode & 0x0FFF;
	}

	private void callsSubroutineAt() {
		this.stack.push(this.programCounter);
		this.programCounter = this.opcode & 0x0FFF;
	}

	private void skipsNextInstrIfVXEqualsNN() {
		int registerPosition = (this.opcode & 0x0F00) >>  8;
		int registerData = (this.opcode & 0x00FF);
		int registerResult = readInRegister(registerPosition);
		this.programCounter += (registerResult == registerData)? 4 : 2; 
	}

	private void skipsNextInstrIfVXDoesnEqualNN() {
		int registerPosition = (this.opcode & 0x0F00) >>  8;
		int registerData = (this.opcode & 0x00FF);
		int registerResult = readInRegister(registerPosition);
		this.programCounter += (registerResult != registerData)? 4 : 2; 
	}

	private void skipsNextInstrIfVXEqualsVY() {
		int registerXPosition = (this.opcode & 0x0F00) >> 8;
		int registerYPosition = (this.opcode & 0x00F0) >> 4;
		int registerXResult = readInRegister(registerXPosition);
		int registerYResult = readInRegister(registerYPosition);
		this.programCounter += (registerXResult == registerYResult)? 4 : 2;	
	}

	private void setsVXToNN() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;
		int registerData = (this.opcode & 0x00FF);
		writeInRegister(registerPosition, registerData);
		this.programCounter += 2; 
	}

	private void addsNNToVX() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;
		int registerData = (this.opcode & 0x00FF);
		int result = (readInRegister(registerPosition) + registerData) & 0xFF;
		writeInRegister(registerPosition, result);
		this.programCounter += 2; 
	}

	private void setsVXToTheValueOfVY() {
		int registerXPosition = (this.opcode & 0x0F00) >> 8;
		int registerYPosition = (this.opcode & 0x00F0) >> 4;
		writeInRegister(registerXPosition, readInRegister(registerYPosition));
		this.programCounter += 2; 
	}

	private void bitwiseOR() {
		int registerXPosition = (this.opcode & 0x0F00) >> 8;
		int registerYPosition = (this.opcode & 0x00F0) >> 4;
		int registerXResult = readInRegister(registerXPosition);
		int registerYResult = readInRegister(registerYPosition);
		
		int result = (registerXResult |= registerYResult);

		result &= 0xFF;
		writeInRegister(registerXPosition, result);
		this.programCounter += 2;
	}

	private void bitwiseAND() {
		int registerXPosition = (this.opcode & 0x0F00) >> 8;
		int registerYPosition = (this.opcode & 0x00F0) >> 4;
		int registerXResult = readInRegister(registerXPosition);
		int registerYResult = readInRegister(registerYPosition);

		int result = (registerXResult &= registerYResult);

		result &= 0xFF;
		writeInRegister(registerXPosition, result);
		this.programCounter += 2; 
	}

	private void bitwiseXOR() {
		int registerXPosition = (this.opcode & 0x0F00) >> 8;
		int registerYPosition = (this.opcode & 0x00F0) >> 4;
		int registerXResult = readInRegister(registerXPosition);
		int registerYResult = readInRegister(registerYPosition);

		int result = (registerXResult ^= registerYResult);

		result &= 0xFF;
		writeInRegister(registerXPosition, result);
		this.programCounter += 2; 
	}

	private void addsVYToVX() {
		int registerXPosition = (this.opcode & 0x0F00) >> 8;
		int registerYPosition = (this.opcode & 0x00F0) >> 4;
		int registerXResult = readInRegister(registerXPosition);
		int registerYResult = readInRegister(registerYPosition);

		int result = (registerXResult + registerYResult);

		if(result > 0xF)
			writeInRegister(0xF, 1);
		else
			writeInRegister(0xF, 0);

		result &= 0xFF;
		writeInRegister(registerXPosition, result);		
		this.programCounter += 2;
	}

	private void subtracVYFromVX() {
		int registerXPosition = (this.opcode & 0x0F00) >> 8;
		int registerYPosition = (this.opcode & 0x00F0) >> 4;
		int registerXResult = readInRegister(registerXPosition);
		int registerYResult = readInRegister(registerYPosition);


		if(registerXResult > registerYResult)
			writeInRegister(0xF, 1);
		else
			writeInRegister(0xF, 0);

		int result = (registerXResult - registerYResult);

		result &= 0xFF;
		writeInRegister(registerXPosition, result);		
		this.programCounter += 2; 
	}

	private void shiftsVXToTheRightBy1() {
		int registerXPosition = (this.opcode & 0x0F00) >> 8;
		int registerXResult = readInRegister(registerXPosition);

		int result = (registerXResult >> 1);

		if((registerXResult & 0x000F) == 1)
			writeInRegister(0xF, 1);
		else
			writeInRegister(0xF, 0);

		result &= 0xFF;
		writeInRegister(registerXPosition, result);
		this.programCounter += 2; 		
	}
	
	private void setsVXToVYMinusVX() {
		int registerXPosition = (this.opcode & 0x0F00) >> 8;
		int registerYPosition = (this.opcode & 0x00F0) >> 4;
		int registerXResult = readInRegister(registerXPosition);
		int registerYResult = readInRegister(registerYPosition);

		if(registerYResult > registerXResult)
			writeInRegister(0xF, 1);
		else
			writeInRegister(0xF, 0);

		int result = (registerYResult - registerXResult);
		
		result &= 0xFF;
		writeInRegister(registerXPosition, result);
		this.programCounter += 2; 
	}

	private void shiftsVXtoTheLeftBy1() {
		int registerXPosition = (this.opcode & 0x0F00) >> 8;
		int registerXResult = readInRegister(registerXPosition);

		int result = (registerXResult << 1);

		if((registerXResult & 0x000F) == 1)
			writeInRegister(0xF, 1);
		else
			writeInRegister(0xF, 0);

		result &= 0xFF;
		writeInRegister(registerXPosition, result);
		this.programCounter += 2;
	}

	private void skipsNextInstructionIfVXdoesnEqualVY() {
		int registerXPosition = (this.opcode & 0x0F00) >> 8;
		int registerYPosition = (this.opcode & 0x00F0) >> 4;
		int registerXResult = readInRegister(registerXPosition);
		int registerYResult = readInRegister(registerYPosition);
		this.programCounter += (registerXResult != registerYResult)? 4 : 2;	
	}

	private void setsItoAddressNNN() {
		this.index = (this.opcode & 0x0FFF);
		this.programCounter += 2;
	}

	private void jumpsAddressNNNPlusV0() {
		this.programCounter = this.index + (this.opcode & 0x0FFF);		
	}

	private void setsVXResultOfABitwiseAndOperationOnARandomNumber() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;
		int registerData = (this.opcode & 0x00FF);

		int result = (registerData & new Random().nextInt(0x100));

		writeInRegister(registerPosition, result);
		this.programCounter += 2;
	}

	private void drawsASprite() {
		int registerXPosition = readInRegister((this.opcode & 0x0F00) >> 8);
		int registerYPosition = readInRegister((this.opcode & 0x00F0) >> 4);
		int nibble = (this.opcode & 0x000F);
		writeInRegister(0xF, 0x0);

		for(int rowY = 0 ; rowY < nibble; rowY++) {
			int line = this.memory.read(this.index + rowY);
			int resultY = registerYPosition + rowY;
			resultY = resultY % 32;

			for(int colX = 0; colX < 8; colX++) {
				int pixel = line & (0x80 >> colX);
				int resultX = registerXPosition + colX; 
				resultX = resultX % 64;

				if(pixel != 0) {
					int indexLocal = resultX + resultY * 64;
					int pixelValue = this.peripherals.readPixelValue(indexLocal);

					if(pixelValue == 1) {
						writeInRegister(0xF, 0x1);
					}
					this.peripherals.writePixelValue(indexLocal, pixelValue ^ 1);					
				}
			}
		}
		
		this.programCounter += 2;
		this.peripherals.repaintScreen();		
	}

	private void skipsTheNextInstructionIfTheKeyStoredInVXIsPressed() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;

		if(this.peripherals.getKeyPressed() == readInRegister(registerPosition)) 
			this.programCounter += 4; 
		else
			this.programCounter += 2;
	}

	private void skipsTheNextInstructionIfTheKeyStoredInVXisNPressed() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;

		if(this.peripherals.getKeyPressed() != readInRegister(registerPosition)) 
			this.programCounter += 4; 
		else
			this.programCounter += 2;
	}

	private void setsVXToTheValueOfTheDelayTimer() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;

		writeInRegister(registerPosition, this.delay);
		this.programCounter += 2;
	}

	private void keyPressIsAwaitedAndThenStoredInVX(){
		int registerPosition = (this.opcode & 0x0F00);
		int currentKey = this.peripherals.getKeyPressed();

		while(currentKey == 0) {
			try {
				Thread.sleep(300);
				currentKey = this.peripherals.getKeyPressed();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		writeInRegister(registerPosition, currentKey);
		this.programCounter += 2;
	}

	private void setsTheDelayTimerToVX() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;
		this.delay = readInRegister(registerPosition);

		this.programCounter += 2;
	}

	private void setsTheSoundTimerToVX() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;
		this.soundTimers = readInRegister(registerPosition);

		this.programCounter += 2;
	}

	private void addsVXToI() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;
		this.index += readInRegister(registerPosition);

		this.programCounter += 2;
	}

	private void setsIToTheLocationOfTheSpriteForTheCharacterInVX() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;
		this.index = (0x050 + readInRegister(registerPosition) * 5);

		this.programCounter += 2;
	}

	private void storesTheBinaryCodedDecimalRepresentationOfVX() {
		int registerPosition = (this.opcode & 0x0F00) >> 8;
		int registerValue = readInRegister(registerPosition);

		int hundreds = (registerValue - (registerValue % 100)) / 100;
		registerValue -= hundreds * 100;
		int tens = (registerValue - (registerValue % 10))/ 10;
		registerValue -= tens * 10; 

		memory.write(this.index, hundreds);
		memory.write(this.index + 1, tens);
		memory.write(this.index + 2, registerValue);

		this.programCounter += 2;
	}

	private void storesV0ToVXInMemoryStartingAtAddressI() {
		int amountOfRegisters = (this.opcode & 0x0F00) >> 8;

		for(int i = 0 ;i <= amountOfRegisters; i++) {
			memory.write(this.index + i, readInRegister(i));
		}

		this.programCounter += 2;
	}

	private void fillsV0ToVXWithValuesFromMemoryStartingAtAddressI() {
		int amountOfRegisters = (this.opcode & 0x0F00) >> 8;

		for(int i = 0 ;i <= amountOfRegisters; i++) {
			writeInRegister(i, memory.read(this.index + i));			
		}

		this.index = (this.index + amountOfRegisters + 1);
		this.programCounter += 2;
	}
}