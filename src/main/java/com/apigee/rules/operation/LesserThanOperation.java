package com.apigee.rules.operation;

/**
 * A concrete implementation of {@link Operation} which performs lesser than operation.
 *
 * @author Anish Kurian.
 * @see com.apigee.rules.operation.AbstractOperation
 */
public class LesserThanOperation extends AbstractOperation {

    /**
     * Performs a lesser than operation between the given numeric operands.
     *
     * @param left  the operand value.
     * @param right the comparison value.
     * @return lesser than operation result.
     */
    @Override
    public boolean operate(final Double left, final Double right) {
        return left < right;
    }

    /**
     * Performs a lesser than operation between the given string operands.
     *
     * @param left  the operand value.
     * @param right the comparison value.
     * @return lesser than operation result.
     */
    @Override
    public boolean operate(final String left, final String right) {
        if (left == right) {
            return false;
        }

        if (left == null) {
            return false;
        }

        return left.compareTo(right) < 0;
    }
}
