package br.com.puppy8.peripherals;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Sound {
    private Synthesizer synthesizer;
    private MidiChannel midiChannel;

    public Sound() {
        try {
        	MidiDevice.Info[] devices = MidiSystem.getMidiDeviceInfo();
        	if(devices.length == 0)
        		System.out.println("Not Found!!");
        	else {	
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            midiChannel = synthesizer.getChannels()[0];
        	}
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (midiChannel != null) {
            midiChannel.noteOn(60, 50);
        }
    }

    public void stop() {
        if (midiChannel != null) {
            midiChannel.noteOff(60);
        }
    }
}
