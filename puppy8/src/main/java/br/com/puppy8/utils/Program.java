package br.com.puppy8.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Program {

	private static DataInputStream inputStream;
	private File file;
	private FileInputStream fileInputStream = null;

	public Program(String[] args) {

		if (args.length > 0) {
			try {
				file = new File(args[0]);
				fileInputStream = new FileInputStream(file);
				Program.inputStream = new DataInputStream(fileInputStream);
			} catch (FileNotFoundException e) {
				System.err.println("Wrong Argument! it must be: \"java -jar <puppy8>.jar <rom-name>.ch8\"");
				System.exit(1);
			}
		}else {
			System.err.println("Argument format: \"java -jar <puppy8>.jar <rom-name>.ch8\"");
			System.exit(1);
		}
	}

	public DataInputStream dataStream() {
		return Program.inputStream;
	}
}
