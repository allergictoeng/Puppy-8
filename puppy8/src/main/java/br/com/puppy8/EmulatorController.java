package br.com.puppy8;

import br.com.puppy8.peripherals.Frame;
import br.com.puppy8.utils.Program;

public class EmulatorController {
	private Emulator emulator;
	private static EmulatorController emulatorController;
	private static Program program;

	public EmulatorController() {}

	public static EmulatorController getInstance(Program program) {
		if(emulatorController == null) {
			EmulatorController.program = program;
			emulatorController = new EmulatorController();
			Frame frame = new Frame(emulatorController);
			frame.setup(null,null);
			frame.setVisible(true);
			frame.runner();
		}
		return emulatorController;
	}

	public void setRunner(Frame frame, String filepath) {
		if(emulator != null && emulator instanceof Emulator) stopRunner();
		emulator = new Emulator(frame, EmulatorController.program);
		emulator.start();
	}

	private void stopRunner() {
		emulator.stopEmulation();
		emulator = null;
	}
}