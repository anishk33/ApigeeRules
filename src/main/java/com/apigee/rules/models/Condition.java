package com.apigee.rules.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Model representing a Condition.
 * Holds the operand, operator, comparison value and data type of the comparison value.
 *
 * @author Anish Kurian.
 */
public class Condition {
    private String operand;
    private Operator operator;
    private Datatype datatype;
    private Object comparisonValue;

    public Condition(final String operand, final Operator operator,
                     final Datatype datatype, final Object comparisonValue) {
        this.operand = operand;
        this.operator = operator;
        this.datatype = datatype;
        this.comparisonValue = comparisonValue;
    }

    public String getOperand() {
        return operand;
    }

    public Operator getOperator() {
        return operator;
    }

    public Datatype getDatatype() {
        return datatype;
    }

    public Object getComparisonValue() {
        return comparisonValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("operand", operand)
                .append("operator", operator)
                .append("datatype", datatype)
                .append("comparisonValue", comparisonValue)
                .toString();
    }
}
