package com.apigee.rules.operation;

/**
 * Denotes and operation with operand value on the left and a comparison value on the right.
 *
 * @author Anish Kurian.
 */
public interface Operation {

    /**
     * Performs the operation on the left and right components of the operation.
     *
     * @param left  the operand value.
     * @param right the comparison value.
     * @return the evaluation result.
     */
    boolean operate(final Object left, final Object right);
}
