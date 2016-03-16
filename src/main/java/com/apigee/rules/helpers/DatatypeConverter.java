package com.apigee.rules.helpers;

import com.apigee.rules.exception.DataNotFoundException;
import com.apigee.rules.exception.InvalidDataException;
import com.apigee.rules.models.Datatype;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * A converter to create concrete objects from {@link String} values.
 *
 * @author Anish Kurian.
 */
public final class DatatypeConverter {

    /**
     * Converts the given {@link String} value to concrete data type based on given {@link Datatype}.
     * Checks the given data type and constructs object of appropriate type.
     *
     * @param value    the value to be converted.
     * @param datatype the {@link Datatype}.
     * @return the converted object.
     * @throws InvalidDataException  is given value is blank.
     * @throws DataNotFoundException if given data type conversion is not supported.
     */
    public static Object convert(final String value, final Datatype datatype) {
        if (StringUtils.isBlank(value)) {
            throw new InvalidDataException("Cannot convert data type for value: " + value);
        }

        switch (datatype) {
            case STRING:
                return new String(value);
            case NUMBER:
                return NumberUtils.createDouble(value);
            default:
                throw new DataNotFoundException(
                        String.format("Converter missing for datatype : %s!", datatype));
        }
    }
}
