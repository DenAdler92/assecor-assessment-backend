package de.adler.assecor_assessment.converter;

import de.adler.assecor_assessment.model.ColorEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ColorEnumConverter implements AttributeConverter<ColorEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ColorEnum colorEnum) {
        return colorEnum != null ? colorEnum.getColorCode() : null;
    }

    @Override
    public ColorEnum convertToEntityAttribute(Integer dbData) {
        return dbData != null ? ColorEnum.fromColorCode(dbData) : null;
    }
}
