package com.apigee.rules.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Model representing an action.
 * Holds the operand, value and data type of the value.
 *
 * @author Anish Kurian.
 */
public class Action {

    private String operand;
    private Datatype valueDatatype;
    private Object value;

    public Action(final String operand, final Datatype valueDatatype, final Object value) {
        this.operand = operand;
        this.valueDatatype = valueDatatype;
        this.value = value;
    }

    public String getOperand() {
        return operand;
    }

    public Datatype getValueDatatype() {
        return valueDatatype;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("operand", operand)
                .append("valueDatatype", valueDatatype)
                .append("value", value)
                .toString();
    }
}
