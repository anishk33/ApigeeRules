package com.apigee.rules.operation;

/**
 * A concrete implementation of {@link Operation} which performs not equal to operation.
 *
 * @author Anish Kurian.
 * @see com.apigee.rules.operation.AbstractOperation
 */
public class NotEqualToOperation extends AbstractOperation {

    /**
     * Performs a not equal to operation between the given numeric operands.
     *
     * @param left  the operand value.
     * @param right the comparison value.
     * @return not equal to operation result.
     */
    @Override
    public boolean operate(final Double left, final Double right) {
        return !left.equals(right);
    }

    /**
     * Performs a not equal to operation between the given string operands.
     *
     * @param left  the operand value.
     * @param right the comparison value.
     * @return not equal to operation result.
     */
    @Override
    public boolean operate(final String left, final String right) {
        if (left == right) {
            return false;
        }

        if (left == null) {
            return false;
        }

        return left.compareTo(right) != 0;
    }
}
