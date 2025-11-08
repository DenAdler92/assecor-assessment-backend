package de.adler.assecor_assessment.model;

public enum ColorEnum {
    BLAU(1, "blau"),
    GRUEN(2, "grün"),
    VIOLETT(3, "violett"),
    ROT(4, "rot"),
    GELB(5, "gelb"),
    TUERKIS(6, "türkis"),
    WEISS(7, "weiß");

    private final int colorCode;
    private final String displayName;

    ColorEnum(int colorCode, String displayName) {
        this.colorCode = colorCode;
        this.displayName = displayName;
    }

    public static ColorEnum fromColorCode(int colorCode) {
        for (ColorEnum color : values()) {
            if (color.colorCode == colorCode) {
                return color;
            }
        }
        throw new IllegalArgumentException("Colorcode not registered: " + colorCode);
    }
}
