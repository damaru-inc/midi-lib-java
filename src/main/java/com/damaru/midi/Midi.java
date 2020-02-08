package com.damaru.midi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;

public class Midi {
	public static final int PPQ = 480; // pulses per quarter note
	public static final int PPS = PPQ * 2; // pulses per second
	public static final int MIDDLE_C = 60; // midi note number.
	public static final double LEGATO = 0.9;
	public static final int PULSES_PER_SIXTEENTH_NOTE = PPQ / 4;
	private static final Logger log = LoggerFactory.getLogger(Midi.class);
	private static Sequencer sequencer;
	private static Synthesizer synthesizer;
	private static final String DEVICE_TO_IGNORE = "foo"; // was "Real Time Sequencer"
	private static final List<InstrumentValue> instruments = new ArrayList();

	public static List<MidiDeviceValue> getDevices() {
		ArrayList<MidiDeviceValue> ret = new ArrayList();
		try {
			for (MidiDevice.Info info : MidiSystem.getMidiDeviceInfo()) {
				String desc = info.getName() + " - " + info.getDescription();
				MidiDevice device = MidiSystem.getMidiDevice(info);
				log.debug(desc + " rec: " + device.getMaxReceivers()
						+ " tra: " + device.getMaxTransmitters() + info);

				if (!desc.startsWith(DEVICE_TO_IGNORE)) {
					MidiDeviceValue val = new MidiDeviceValue(device, desc);
					ret.add(val);
				}
			}
		} catch (MidiUnavailableException e) {
			log.error("Midi unavailable", e);
		}
		return ret;
	}

	public static List<MidiDeviceValue> getReceivers() {
		ArrayList<MidiDeviceValue> ret = new ArrayList();
		try {
			for (MidiDevice.Info info : MidiSystem.getMidiDeviceInfo()) {
				String desc = info.getName() + " - " + info.getDescription();
				MidiDevice device = MidiSystem.getMidiDevice(info);
				log.debug(desc + " rec: " + device.getMaxReceivers()
						+ " tra: " + device.getMaxTransmitters() + info);

				if (device.getMaxReceivers() != 0 && !desc.startsWith(DEVICE_TO_IGNORE)) {
					MidiDeviceValue val = new MidiDeviceValue(device, desc);
					ret.add(val);
				}
			}
		} catch (MidiUnavailableException e) {
			log.error("Midi unavailable", e);
		}
		return ret;
	}

	public static List<MidiDeviceValue> getTransmitters() {
		ArrayList<MidiDeviceValue> ret = new ArrayList();
		try {
			for (MidiDevice.Info info : MidiSystem.getMidiDeviceInfo()) {
				String desc = info.getName() + " - " + info.getDescription();
				MidiDevice device = MidiSystem.getMidiDevice(info);
				log.debug(desc + " rec: " + device.getMaxReceivers()
						+ " tra: " + device.getMaxTransmitters() + info);

				if (device.getMaxTransmitters() != 0 && !desc.startsWith(DEVICE_TO_IGNORE)) {
					MidiDeviceValue val = new MidiDeviceValue(device, desc);
					ret.add(val);
				}
			}
		} catch (MidiUnavailableException e) {
			log.error("Midi unavailable", e);
		}
		return ret;
	}

	public static List<InstrumentValue> getInstruments() throws MidiUnavailableException {
		if (instruments.isEmpty()) {
			synthesizer = MidiSystem.getSynthesizer();
			Soundbank sb = synthesizer.getDefaultSoundbank();
			for (Instrument i : sb.getInstruments()) {
				instruments.add(new InstrumentValue(i));
				// log.debug("instrument: " + i.getName() + " " +
				// i.getPatch().getBank() + " " + i.getPatch().getProgram());
			}
		}
		return instruments;
	}

	public static MidiEvent createNoteOnEvent(int key, int velocity, long tick) throws Exception {
		//log.debug("key: " + key + " vel: " + velocity + " tick: " + tick);
		return createNoteEvent(ShortMessage.NOTE_ON, key, velocity, tick);
	}

	public static MidiEvent createNoteOffEvent(int key, long tick) throws Exception {
		return createNoteEvent(ShortMessage.NOTE_OFF, key, 0, tick);
	}

	public static MidiEvent createNoteEvent(int command, int key, int velocity, long tick) throws Exception {
		//log.debug("command: " + command + " key: " + key + " vel: " + velocity + " tick: " + tick);
		ShortMessage message = createShortMessage(command, key, velocity);
		MidiEvent event = new MidiEvent(message, tick);
		return event;
	}

	public static ShortMessage createNoteOnMessage(int key, int velocity) throws Exception {
		return createShortMessage(ShortMessage.NOTE_ON, key, velocity);
	}

	public static ShortMessage createNoteOffMessage(int key, int velocity) throws Exception {
		return createShortMessage(ShortMessage.NOTE_OFF, key, velocity);
	}

	public static ShortMessage createShortMessage(int command, int key, int velocity) throws Exception {
		ShortMessage message = new ShortMessage();
		message.setMessage(command,
				0, // always on channel 1
				key,
				velocity);
		return message;
	}

}
