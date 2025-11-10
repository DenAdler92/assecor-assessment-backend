package de.adler.assecor_assessment.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorEnumTest {

    @Test
    public void fromColorCode() {
        ColorEnum colorEnum = ColorEnum.fromColorCode(1);
        Assertions.assertEquals(ColorEnum.BLAU, colorEnum);
    }

    @Test
    public void fromColorCodeNotExistent() {
         Assertions.assertThrows(IllegalArgumentException.class, () -> ColorEnum.fromColorCode(123));
    }

    @Test
    public void fromDisplayName() {
        ColorEnum colorEnum = ColorEnum.fromDisplayName("blau");
        Assertions.assertEquals(ColorEnum.BLAU, colorEnum);
    }

    @Test
    public void fromDisplayNameNotExistent() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ColorEnum.fromDisplayName("NoColor"));
    }
}
