package com.damaru.music;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTest {

	@Test
	public void testSharpName() {
		Note note = new Note(61);
		String name = note.getName();
		String expected = "C#4";
		assertEquals(expected, name);
	}

	@Test
	public void testFlatName() {
		Note note = new Note(61);
		String name = note.getName(Note.Accidental.FLAT);
		String expected = "Db4";
		assertEquals(expected, name);
	}


	@Test
	public void testGetFullName() {
		Note note = new Note(61, Note.Accidental.FLAT);
		String expected = "Db4";
		assertEquals("Db4", note.getName());
	}

}