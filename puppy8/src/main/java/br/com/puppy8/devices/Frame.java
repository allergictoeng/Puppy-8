package br.com.puppy8.devices;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private static final int WIDTH = 640;
	private static final int HEIGHT = 320;
	private Panel panel;

	public void setup(Panel panel) {
		this.panel = panel;

		int width = WIDTH + getInsets().right + getInsets().left;
		int height = HEIGHT + getInsets().top + getInsets().bottom;
		setPreferredSize(new Dimension(width, height));
		pack();
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Puppy-8");
        setResizable(false);
        setFocusable(true);
        repaint();
        add(panel);
	}

}
