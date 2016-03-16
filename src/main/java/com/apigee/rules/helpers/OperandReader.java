package com.apigee.rules.helpers;

import com.apigee.rules.exception.InvalidDataException;
import com.apigee.rules.io.ApplicationIO;
import com.apigee.rules.models.Datatype;

import java.util.Map;

/**
 * A helper implementation to read operand value.
 * Attempts to read the operand value from given operand map or as input.
 *
 * @author Anish Kurian.
 */
public class OperandReader {

    private Map<String, Object> operandMap;
    private ApplicationIO applicationIO;

    public OperandReader(final Map<String, Object> operandMap) {
        this(operandMap, new ApplicationIO());
    }

    public OperandReader(final Map<String, Object> operandMap, final ApplicationIO applicationIO) {
        if (operandMap == null) {
            throw new InvalidDataException("Cannot have operand reader with out operand map!");
        }
        this.operandMap = operandMap;
        this.applicationIO = applicationIO;
    }

    /**
     * Reads the value for given key from operand map. If the value is not present in the operand map,
     * reads the value from input.
     *
     * @param key the key for which value is to be retrieved.
     * @return the value.
     */
    public Object read(final String key) {
        Object value = operandMap.get(key);

        if (value == null) {
            return readFromReader(key);
        }
        return value;
    }

    /**
     * Reads the value for given operand as input and converts to appropriate data type
     * using {@link DatatypeConverter}.
     *
     * @param key the key for which value is to be retrieved.
     * @return the value.
     */
    private Object readFromReader(final String key) {
        String operandValue = applicationIO.getString("Enter operand value for " + key);
        return DatatypeConverter.convert(operandValue, Datatype.getDatatypeForValue(operandValue));
    }
}
