package com.damaru.midi;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import java.util.List;

import static org.junit.Assert.*;

public class MidiTest {
	private static final Logger log = LoggerFactory.getLogger(MidiTest.class);

	@Test
	public void testDevices() {
		List<MidiDeviceValue> devices = Midi.getDevices();
		for (MidiDeviceValue d : devices) {
			final MidiDevice.Info di = d.getMidiDevice().getDeviceInfo();
			log.info(d.toString() + "----" + di.getDescription() + "---" + di.getName() + "---" + di.getVendor() + "---" + di.getVersion());
		}
	}

	@Test
	public void testInstruments() throws MidiUnavailableException {
		final List<InstrumentValue> instruments = Midi.getInstruments();
		for (InstrumentValue i : instruments) {
			log.info(i.toString());
		}
	}

}