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
		Note keyNote = new Note(0);
		List<Note> notes = ScaleFactory.generateNoteList(ScaleFactory.MAJOR_SCALE, keyNote,60, 72);

		for (Note note : notes) {
			log.info(note.toString());
		}

		assertEquals(8, notes.size());
		assertEquals("C4", notes.get(0).getName());
		assertEquals("B4", notes.get(6).getName());
	}

	@Test
	void wholeToneTest() {
		Note keyNote = new Note(0);
		List<Note> notes = ScaleFactory.generateNoteList(ScaleFactory.WHOLE_TONE_SCALE, keyNote,60, 72);

//		for (Note note : notes) {
//			log.info(note.toString());
//		}

		assertEquals(7, notes.size());
		assertEquals("C4", notes.get(0).getName());
		assertEquals("A#4", notes.get(5).getName());
	}

	@Test
	void scaleStartingOnSecondDegreeTest() {
		Note keyNote = new Note(0);
		List<Note> notes = ScaleFactory.generateNoteList(ScaleFactory.MAJOR_SCALE, keyNote,61, 74);
		assertEquals(8, notes.size());
		assertEquals("D4", notes.get(0).getName());
		assertEquals("F4", notes.get(2).getName());
		assertEquals("C5", notes.get(6).getName());
		assertEquals("D5", notes.get(7).getName());
	}

}