package com.damaru.music;

public enum ScaleTypeName {
    MAJOR("Major"),
    MINOR("Minor"),
    CHROMATIC("Chromatic"),
    WHOLE_TONE("Whole Tone"),
    WHOLE_HALF("Whole Half"),
    EASTERN("Eastern");

    private String description;

    ScaleTypeName(String description) {
        this.description = description;
    }

    public String toString() {
        return description;
    }
}
