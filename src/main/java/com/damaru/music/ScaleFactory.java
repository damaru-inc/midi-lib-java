package com.damaru.music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScaleFactory {

	// Default accidentals for each major scale
	private static Note.Accidental[] MAJOR_ACCIDENTALS = {
			Note.Accidental.SHARP, // C
			Note.Accidental.SHARP, // C#
			Note.Accidental.SHARP, // D
			Note.Accidental.FLAT,  // Eb
			Note.Accidental.SHARP, // E
			Note.Accidental.FLAT,  // F
			Note.Accidental.SHARP, // F#
			Note.Accidental.SHARP, // G
			Note.Accidental.FLAT,  // Ab
			Note.Accidental.SHARP, // A
			Note.Accidental.FLAT, // Bb
			Note.Accidental.SHARP // B
	};

	// Default accidentals for each minor scale
	private static Note.Accidental[] MINOR_ACCIDENTALS = {
			Note.Accidental.FLAT, // C
			Note.Accidental.SHARP, // C#
			Note.Accidental.FLAT, // D
			Note.Accidental.FLAT,  // Eb
			Note.Accidental.FLAT, // E
			Note.Accidental.FLAT,  // F
			Note.Accidental.SHARP, // F#
			Note.Accidental.FLAT, // G
			Note.Accidental.FLAT,  // Ab
			Note.Accidental.FLAT, // A
			Note.Accidental.FLAT, // Bb
			Note.Accidental.SHARP // B
	};

	public static ScaleType MAJOR_SCALE = new ScaleType("Major", new int[] {2, 2, 1, 2, 2, 2, 1}, MAJOR_ACCIDENTALS);
	public static ScaleType MINOR_SCALE = new ScaleType("Minor", new int[] {2, 1, 2, 2, 1, 2, 2}, MINOR_ACCIDENTALS);
	public static ScaleType WHOLE_TONE_SCALE = new ScaleType("Whole Tone", new int[] {2});
	public static ScaleType WHOLE_HALF_SCALE = new ScaleType("Whole Half", new int[] {2, 1});
	public static ScaleType CHROMATIC_SCALE = new ScaleType("Chromatic", new int[] {1});
	public static ScaleType EASTERN = new ScaleType("Eastern", new int[] {2, 1, 3, 1, 1, 3, 1});

	private static HashMap<ScaleTypeName, ScaleType> scaleTypeMap = new HashMap<>();

	public static Note CHROMATICS_SHARP[] = new Note[12];
	public static Note CHROMATICS_FLAT[] = new Note[12];

	static {
		for (int i = 0; i < 12; i++) {
			CHROMATICS_SHARP[i] = new Note(i, Note.Accidental.SHARP);
			CHROMATICS_FLAT[i] = new Note(i, Note.Accidental.FLAT);
		}

		scaleTypeMap.put(ScaleTypeName.MAJOR, MAJOR_SCALE);
		scaleTypeMap.put(ScaleTypeName.MINOR, MINOR_SCALE);
		scaleTypeMap.put(ScaleTypeName.CHROMATIC, CHROMATIC_SCALE);
		scaleTypeMap.put(ScaleTypeName.WHOLE_TONE, WHOLE_TONE_SCALE);
		scaleTypeMap.put(ScaleTypeName.WHOLE_HALF, WHOLE_HALF_SCALE);
		scaleTypeMap.put(ScaleTypeName.EASTERN, EASTERN);
	}

	public static List<Note> generateNoteList(ScaleType scaleType, int startScaleIndex, int startNoteNum, int endNoteNum) {
		List<Note> notes = new ArrayList<>();
		int scaleIndex = startScaleIndex;
		int[] intervals = scaleType.getIntervals();
		int curNoteNum = startNoteNum;

		while (curNoteNum <= endNoteNum) {
			Note n = new Note(curNoteNum, scaleType.getAccidental(curNoteNum));
			notes.add(n);
			int interval = intervals[scaleIndex];
			curNoteNum += interval;
			scaleIndex++;
			if (scaleIndex >= intervals.length) {
				scaleIndex = 0;
			}
		}

		return notes;
	}

	public static List<Note> generateNoteList(ScaleTypeName scaleTypeName, Note keyNote, int startNoteNum, int endNoteNum) {
		ScaleType scaleType = scaleTypeMap.get(scaleTypeName);

		if (scaleType == null) {
			throw new RuntimeException("No entry in scaleTypeMap for " + scaleTypeName);
		}

		return generateNoteList(scaleType, keyNote, startNoteNum, endNoteNum);
	}

	public static List<Note> generateNoteList(ScaleType scaleType, Note keyNote, int startNoteNum, int endNoteNum) {
		Scale scale = new Scale(scaleType, keyNote);
		Note lowNote = scale.getClosestNote(startNoteNum);
		int actualStartNumber = lowNote.getNoteNum();
		int startScaleIndex = scale.getScaleIndex(lowNote);
		return generateNoteList(scaleType, startScaleIndex, actualStartNumber, endNoteNum);
	}



}
