package com.damaru.music;

import java.util.ArrayList;
import java.util.List;

public class ScaleFactory {

	public static Scale MAJOR_SCALE = new Scale(new int[] {2, 2, 1, 2, 2, 2, 1});
	public static Scale MINOR_SCALE = new Scale(new int[] {2, 1, 2, 2, 1, 2, 2});
	public static Scale WHOLE_TONE_SCALE = new Scale(new int[] {2});
	public static Scale WHOLE_HALF_SCALE = new Scale(new int[] {2, 1});


	public static List<Note> generateScale(Scale scale, int startNote, int endNote) {
		List<Note> notes = new ArrayList<>();
		int scaleIndex = 0;
		int[] intervals = scale.getIntervals();

		for (int i = startNote; i <= endNote;) {
			Note n = new Note(i);
			notes.add(n);
			int interval = intervals[scaleIndex];
			i += interval;
			scaleIndex++;
			if (scaleIndex >= intervals.length) {
				scaleIndex = 0;
			}
		}

		return notes;
	}
}
