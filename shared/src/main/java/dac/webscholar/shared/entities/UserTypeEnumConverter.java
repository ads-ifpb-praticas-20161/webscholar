package dac.webscholar.shared.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by marcusviniv on 26/09/2016.
 */
@Converter

public class UserTypeEnumConverter  implements AttributeConverter<UserType, String> {


    @Override
    public String convertToDatabaseColumn(UserType enumType) {
        return enumType.getColumnValue();
    }

    @Override
    public UserType convertToEntityAttribute(String s) {
        return UserType.getTypeFromColumnValue(s);
    }
}
