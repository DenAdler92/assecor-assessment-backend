package de.adler.assecor_assessment.converter;

import de.adler.assecor_assessment.model.ColorEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ColorEnumConverterTest {

    private ColorEnumConverter colorEnumConverter;

    @BeforeEach
    void prepareTestEnvironment() {
        colorEnumConverter = new ColorEnumConverter();
    }

    @Test
    void convertToDatabaseColumnTest() {
        int dbInteger = colorEnumConverter.convertToDatabaseColumn(ColorEnum.BLAU);
        Assertions.assertEquals(ColorEnum.BLAU.getColorCode(), dbInteger);
    }

    @Test
    void convertToDatabaseColumnReturnsNullWhenColorEnumIsNull() {
        Assertions.assertNull(colorEnumConverter.convertToDatabaseColumn(null));
    }

    @Test
    void convertToEntityAttributeTest() {
        ColorEnum colorEnum = colorEnumConverter.convertToEntityAttribute(1);
        Assertions.assertEquals(ColorEnum.BLAU, colorEnum);
    }

    @Test
    void convertToEntityAttributeReturnsNullWhenDbDataIsNull() {
        Assertions.assertNull(colorEnumConverter.convertToEntityAttribute(null));
    }


}
