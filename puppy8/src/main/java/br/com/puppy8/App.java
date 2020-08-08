package br.com.puppy8;

import br.com.puppy8.utils.Program;

public class App{
	
	public static void main( String[] args ){
		EmulatorController.getInstance(new Program(args));
	}
}
