package com.apigee.rules.models;

import com.apigee.rules.exception.InvalidDataException;
import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.math.NumberUtils.isNumber;

/**
 * Holds different data types that are supported in the rule.
 *
 * @author Anish Kurian.
 */
public enum Datatype {
    STRING,
    NUMBER;

    /**
     * Retrieves the data type for given value.
     * Checks if given value is a Number. If number, returns {@link #NUMBER}; else returns {@link #STRING}.
     *
     * @param value the value for which data type is to be retrieved.
     * @return the {@link Datatype}.
     */
    public static Datatype getDatatypeForValue(final String value) {
        if (StringUtils.isBlank(value)) {
            throw new InvalidDataException("Cannot find data type for value :" + value);
        }

        if (isNumber(value)) {
            return NUMBER;
        }

        return STRING;
    }
}
