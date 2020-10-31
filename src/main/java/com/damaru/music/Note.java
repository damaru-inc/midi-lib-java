package com.damaru.music;

import java.util.Objects;

public class Note {

	private static final String[] SHARP_NAMES = { "C", "C#", "D", "D#", "E", "F",
		"F#", "G", "G#", "A", "A#", "B"};

	private static final String[] FLAT_NAMES = { "C", "Db", "D", "Eb", "E", "F",
			"Gb", "G", "Ab", "A", "Bb", "B"};

	public enum Accidental {SHARP, FLAT}

	private int noteNum;
	private Accidental accidental = Accidental.SHARP;

	public Note(int noteNum) {
		this.noteNum = noteNum;
	}

	public Note(int noteNum, Accidental accidental) {
		this.noteNum = noteNum;
		this.accidental = accidental;
	}

	public String getName() {
		return getName(accidental);
	}

	public String getName(Accidental accidental) {
		int octave = noteNum / 12 - 1;
		int degree = noteNum % 12;
		return (accidental == Accidental.SHARP ? SHARP_NAMES[degree] : FLAT_NAMES[degree]) + octave;
	}

	public String getSimpleName() {
		return getSimpleName(accidental);
	}

	public String getSimpleName(Accidental accidental) {
		int degree = noteNum % 12;
		return (accidental == Accidental.SHARP ? SHARP_NAMES[degree] : FLAT_NAMES[degree]);
	}

	public int getNoteNum() {
		return noteNum;
	}

	@Override
	public String toString() {
		return getName() + ":" + noteNum;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Note note = (Note) o;
		return noteNum == note.noteNum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(noteNum);
	}
}
