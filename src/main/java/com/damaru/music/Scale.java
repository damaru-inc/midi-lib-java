package com.damaru.music;

import java.util.ArrayList;
import java.util.List;

public class Scale {

    private String name;
    private ScaleType scaleType;
    private Note keyNote;
    private Note.Accidental accidental;
    private List<Note> notes;

    public Scale(ScaleType scaleType, Note keyNote) {
        this.scaleType = scaleType;
        this.accidental = scaleType.getAccidental(keyNote.getNoteNum());
        this.keyNote = new Note(keyNote.getNoteNum(), accidental);
        this.name = scaleType.getName(keyNote);
        notes = new ArrayList<>();

        int[] intervals = scaleType.getIntervals();
        int noteNum = keyNote.getNoteNum();

        for (int i = 0; i < intervals.length; i++) {
            notes.add(new Note(noteNum, accidental));
            noteNum += intervals[i];
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScaleType getScaleType() {
        return scaleType;
    }

    public void setScaleType(ScaleType scaleType) {
        this.scaleType = scaleType;
    }

    public Note getKeyNote() {
        return keyNote;
    }

    public void setKeyNote(Note keyNote) {
        this.keyNote = keyNote;
    }

    public Note.Accidental getAccidental() {
        return accidental;
    }

    public void setAccidental(Note.Accidental accidental) {
        this.accidental = accidental;
    }

    public Note getLastNote() {
        int len = notes.size();
        Note ret = notes.get(len-1);
        return ret;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public Note getClosestNote(int startNoteNum) {
        int degree = startNoteNum % 12;
        int closestDegree = 0;
        int octave = startNoteNum / 12;
        Note ret = null;

        for (Note note : notes) {
            closestDegree = note.getNoteNum();
            if (closestDegree >= degree) {
                break;
            }
        }

        return new Note(closestDegree + (octave * 12));
    }

    public int getScaleIndex(Note note) {
        int ret = -1;
        int degree = note.getNoteNum() % 12;
        int i = 0;
        for (Note n : notes) {
            int deg = n.getNoteNum() % 12;
            if (degree == deg) {
                ret = i;
                break;
            }
            i++;
        }

        if (ret == -1) {
            throw new RuntimeException(String.format("Note %s does not appear in scale %s", note, name));
        }

        return ret;
    }
}
