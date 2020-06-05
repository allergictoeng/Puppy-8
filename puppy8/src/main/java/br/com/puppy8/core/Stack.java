package br.com.puppy8.core;

import java.util.Arrays;

public class Stack {
	
	static final int STACK_SIZE16 = 0x10;
	private int stackSize;
	private int[] stack;
	private int top = -1;
	private int size = 0;
	
	public Stack(int stackSize) {
		this.stackSize = stackSize;
		this.stack = new int[this.stackSize];
	}

	public int pop() {
		if(this.size == 0)
			return 0;
		
		this.size--;
		int result = this.stack[top];
		this.stack[top] = 0;
		this.top--;
		
		return result;
	}

	public boolean push(int i) {
		if(isFull())
			return false;
		
		this.size++;
		this.stack[++top] = i;
		return true;
	}
	
	public boolean isFull() {
		return this.size == this.stackSize;
	}
	
	public int stackSize() {
		return this.size;
	}
	
	public void print() {
		System.out.println(Arrays.toString(stack));
	}
	
	public String toString() {
		return Arrays.toString(stack);
	}

}
