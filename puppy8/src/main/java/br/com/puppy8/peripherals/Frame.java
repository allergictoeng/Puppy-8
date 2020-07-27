package br.com.puppy8.peripherals;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import br.com.puppy8.EmulatorController;

public class Frame extends JFrame {
	private static final int WIDTH = 640;
	private static final int HEIGHT = 340;
	private Panel panel;
	private EmulatorController emulatorController;

	public Frame(EmulatorController emulatorController) {
		this.emulatorController = emulatorController;
	}

	public void setup(Panel panel) {
		this.panel = panel;

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
				
		setLayout(new BorderLayout());
				 
		pack();
		
		setTitle("Puppy-8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setFocusable(true);
		
		
		if(this.panel != null) {
			add(this.panel);
		}
		
	}
	
	public void runner() {
		emulatorController.setRunner(this, "");
	}
	
}
