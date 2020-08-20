package br.com.puppy8.peripherals;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import br.com.puppy8.EmulatorController;

public class Frame extends JFrame {
	private static final int WIDTH = 640;
	private static final int HEIGHT = 350;
	private static final String NAME = "Puppy-8";
	private Panel panel;
	private EmulatorController emulatorController;
	private KeyListener hexadecimaKeypad;

	public Frame(EmulatorController emulatorController) {
		this.emulatorController = emulatorController;
	}

	public void setup(Panel panel, KeyListener hexadecimaKeypad) {
		this.panel = panel;
		this.hexadecimaKeypad = hexadecimaKeypad;

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
				
		setLayout(new BorderLayout());
				 
		pack();
		
		setTitle(NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setFocusable(true);
		
		if(this.panel != null && this.hexadecimaKeypad != null) {
			add(this.panel);
			addKeyListener(this.hexadecimaKeypad);
		}
	}
	
	public void runner() {
		emulatorController.setRunner(this, "");
	}
}
