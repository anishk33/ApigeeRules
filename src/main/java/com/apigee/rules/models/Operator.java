package com.apigee.rules.models;

import com.apigee.rules.exception.DataNotFoundException;
import com.apigee.rules.exception.InvalidDataException;
import com.apigee.rules.operation.EqualToOperation;
import com.apigee.rules.operation.GreaterThanOperation;
import com.apigee.rules.operation.GreaterThanOrEqualToOperation;
import com.apigee.rules.operation.LesserThanOperation;
import com.apigee.rules.operation.LesserThanOrEqualToOperation;
import com.apigee.rules.operation.NotEqualToOperation;
import com.apigee.rules.operation.Operation;
import org.apache.commons.lang3.StringUtils;

/**
 * Holds different operators that are supported.
 * Each operator defines the symbol used in the expression and is linked to
 * it's operation implementation.
 *
 * @author Anish Kurian.
 */
public enum Operator {

    LESSER_THAN("<", new LesserThanOperation()),
    GREATER_THAN(">", new GreaterThanOperation()),
    EQUAL_TO("==", new EqualToOperation()),
    NOT_EQUAL_TO("!=", new NotEqualToOperation()),
    LESSER_THAN_OR_EQUAL("<=", new LesserThanOrEqualToOperation()),
    GREATER_THAN_OR_EQUAL(">=", new GreaterThanOrEqualToOperation());

    private String value;
    private Operation operation;

    Operator(final String value, final Operation operation) {
        this.value = value;
        this.operation = operation;
    }

    /**
     * Retrieves {@link Operator} for given value(symbol).
     *
     * @param value the symbol of the operator.
     * @return the {@link Operator}.
     * @throws InvalidDataException  if the given value is blank.
     * @throws DataNotFoundException if an operator was not found for given value.
     */
    public static Operator fromValue(final String value) {
        if (StringUtils.isBlank(value)) {
            throw new InvalidDataException("Cannot find operator for " + value);
        }

        for (Operator operator : Operator.values()) {
            if (operator.value.equals(value)) {
                return operator;
            }
        }
        throw new DataNotFoundException("Could not find operator for " + value);
    }

    public String getValue() {
        return value;
    }

    public Operation getOperation() {
        return operation;
    }

    /**
     * A decorator method for {@link Operation#operate(Object, Object)} which could be used
     * to perform the evaluation operation.
     *
     * @param left  the operand value in the operation.
     * @param right the comparison value in the operation.
     * @return the evaluation result.
     */
    public boolean operate(final Object left, final Object right) {
        return operation.operate(left, right);
    }
}
