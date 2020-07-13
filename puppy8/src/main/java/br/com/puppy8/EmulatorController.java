package br.com.puppy8;

import br.com.puppy8.peripherals.Frame;

public class EmulatorController {
	private Emulator emulator;
	private static EmulatorController emulatorController;
	
	public EmulatorController() {}
	
	public static EmulatorController getInstance() {
		if(emulatorController == null) {
			emulatorController = new EmulatorController();
			Frame frame = new Frame(emulatorController);
			frame.setup(null);
			frame.setVisible(true);
		}
		return emulatorController;
	}
	
	public void setRunner(Frame frame, String filepath) {
		if(emulator != null && emulator instanceof Emulator) stopRunner();
		//emulator.clearScreen();
		emulator = new Emulator(frame);
		emulator.start();
	}
	
	private void stopRunner() {
		emulator.stopEmulation();
		emulator = null;
	}
	
}
