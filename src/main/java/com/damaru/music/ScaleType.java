package com.damaru.music;

public class ScaleType {

	private String name;

	private int[] intervals;
	Note.Accidental[] defaultAccidentals;

	public ScaleType(String name, int[] intervals) {
		this.name = name;
		this.intervals = intervals;
	}

	public ScaleType(String name, int[] intervals, Note.Accidental[] defaultAccidentals) {
		this.name = name;
		this.intervals = intervals;
		this.defaultAccidentals = defaultAccidentals;
	}

	public String getName() {
		return name;
	}

	public String getName(Note keyNote) {
		Note.Accidental accidental = getAccidental(keyNote.getNoteNum());
		String ret = keyNote.getSimpleName(accidental) + " " + name;
		return ret;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getIntervals() {
		return intervals;
	}

	public Note.Accidental getAccidental(int noteNum) {
		Note.Accidental ret = Note.Accidental.SHARP;

		if (defaultAccidentals != null) {
			int degree = noteNum % 12;
			ret = defaultAccidentals[degree];
		}

		return ret;
	}

	public int getNumNotes() {
		return intervals.length;
	}
}
