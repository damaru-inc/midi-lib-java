package com.damaru.music;

public class Note {

	private static final String[] SHARP_NAMES = { "C", "C#", "D", "D#", "E", "F",
		"F#", "G", "G#", "A", "A#", "B"};

	private static final String[] FLAT_NAMES = { "C", "Db", "D", "Eb", "E", "F",
			"Gb", "G", "Ab", "A", "Bb", "B"};

	public enum Accidental {SHARP, FLAT}

	private int noteNum;
	private static Accidental defaultAccidental = Accidental.SHARP;

	public static void setDefaultAccidental(Accidental accidental) {
		defaultAccidental = accidental;
	}

	public Note(int noteNum) {
		this.noteNum = noteNum;
	}

	public String getName() {
		return getName(defaultAccidental);
	}

	public String getName(Accidental accidental) {
		int octave = noteNum / 12 - 1;
		int noteNum = this.noteNum % 12;
		return (accidental == Accidental.SHARP ? SHARP_NAMES[noteNum] : FLAT_NAMES[noteNum]) + octave;
	}

	public int getNoteNum() {
		return noteNum;
	}

	@Override
	public String toString() {
		return "Note{" +
				"noteNum=" + noteNum + " " + getName() +
				'}';
	}
}
