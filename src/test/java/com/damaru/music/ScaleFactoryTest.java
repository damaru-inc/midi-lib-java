package com.damaru.music;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScaleFactoryTest {

	private static final Logger log = LoggerFactory.getLogger(ScaleFactoryTest.class);

	@Test
	void majorScaleTest() {
		List<Note> notes = ScaleFactory.generateScale(ScaleFactory.MAJOR_SCALE, 60, 72);

		for (Note note : notes) {
			log.info(note.toString());
		}

		assertEquals(8, notes.size());
		assertEquals("C4", notes.get(0).getName());
		assertEquals("B4", notes.get(6).getName());
	}

	@Test
	void wholeToneTest() {
		List<Note> notes = ScaleFactory.generateScale(ScaleFactory.WHOLE_TONE_SCALE, 60, 72);

		for (Note note : notes) {
			log.info(note.toString());
		}

		assertEquals(7, notes.size());
		assertEquals("C4", notes.get(0).getName());
		assertEquals("A#4", notes.get(5).getName());
	}

}