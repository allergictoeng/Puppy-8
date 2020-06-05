package br.com.puppy8.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestStack {
	
	Stack stack;
	
	@Before
	public void create() {
		stack = new Stack(Stack.STACK_SIZE16);
	}
	
	@Test
	public void stackEmpty() {
		assertFalse(stack.isFull());
	}
	
	@Test
	public void pushInStack() {
		stack.push(1);
		assertTrue((stack.stackSize() == 1));
	}
	
	@Test
	public void popInStack() {
		stack.push(2);
		assertTrue((stack.pop() == 2));		
	}
	
	@Test
	public void pushAndPopInStack() {
		stack.push(2);
		stack.push(3);
		stack.pop();
		assertTrue((stack.stackSize() == 1));
	}
	
	@Test
	public void pushAndPopInStackString() {
		stack.push(5);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		stack.push(1);
		stack.pop();
		assertTrue(stack.toString().equals("[5, 4, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]"));
	}
}
