package dac.webscholar.shared.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by marcusviniv on 27/09/2016.
 */
@Converter
public class DayEnumTypeConverter implements AttributeConverter<DayEnum, String> {

    @Override
    public String convertToDatabaseColumn(DayEnum enumType) {
        return enumType.getColumnValue();
    }

    @Override
    public DayEnum convertToEntityAttribute(String s) {
        return DayEnum.getDayEnum(s);
    }
}
