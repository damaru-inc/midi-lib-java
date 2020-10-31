package com.damaru.music;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScaleTest {

    @Test
    public void testGetClosestNote() {
        Scale scale = new Scale(ScaleFactory.MAJOR_SCALE, new Note(0));
        Note c = new Note(60);
        Note bb = new Note(70); // B flat

        Note note = scale.getClosestNote(60); // C
        assertEquals(60, note.getNoteNum());

        note = scale.getClosestNote(61); // Db
        assertEquals(62, note.getNoteNum()); // D

        note = scale.getClosestNote(70);  // Bb
        assertEquals(71, note.getNoteNum()); // B

        scale = new Scale(ScaleFactory.MAJOR_SCALE, new Note(3)); // Eb major

        note = scale.getClosestNote(64); // E
        assertEquals(65, note.getNoteNum()); // F
    }

    @Test
    public void testLastNote() {
        Scale scale = new Scale(ScaleFactory.MAJOR_SCALE, new Note(2)); // D Major
        Note lastNote = scale.getLastNote();
        assertEquals(13, lastNote.getNoteNum()); // C#
        scale = new Scale(ScaleFactory.MINOR_SCALE, new Note(2)); // D Minor
        lastNote = scale.getLastNote();
        assertEquals(12, lastNote.getNoteNum()); // C
    }

    @Test
    public void testGetNotes() {
        Scale scale = new Scale(ScaleFactory.MAJOR_SCALE, new Note(0));
        List<Note> notes = scale.getNotes();
        assertEquals(7, notes.size());
        assertEquals(0, notes.get(0).getNoteNum());
        assertEquals(2, notes.get(1).getNoteNum());
        assertEquals(4, notes.get(2).getNoteNum());
        assertEquals(5, notes.get(3).getNoteNum());
        assertEquals(7, notes.get(4).getNoteNum());
        assertEquals(9, notes.get(5).getNoteNum());
        assertEquals(11, notes.get(6).getNoteNum());

        scale = new Scale(ScaleFactory.MINOR_SCALE, new Note(0));
        notes = scale.getNotes();
        assertEquals(7, notes.size());
        assertEquals(0, notes.get(0).getNoteNum());
        assertEquals(2, notes.get(1).getNoteNum());
        assertEquals(3, notes.get(2).getNoteNum());
        assertEquals(5, notes.get(3).getNoteNum());
        assertEquals(7, notes.get(4).getNoteNum());
        assertEquals(8, notes.get(5).getNoteNum());
        assertEquals(10, notes.get(6).getNoteNum());

        scale = new Scale(ScaleFactory.WHOLE_TONE_SCALE, new Note(0));
        notes = scale.getNotes();
        assertEquals(1, notes.size());
        assertEquals(0, notes.get(0).getNoteNum());
    }

 }
