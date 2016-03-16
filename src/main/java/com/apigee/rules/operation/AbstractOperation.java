package com.apigee.rules.operation;

import static org.apache.commons.lang3.math.NumberUtils.isNumber;
import static org.apache.commons.lang3.math.NumberUtils.toDouble;

/**
 * Abstract implementation of {@link Operation}.
 * Follows a template pattern where the abstract implementation invokes the concrete implementation
 * based on polymorphism.
 *
 * @author Anish Kurian.
 */
public abstract class AbstractOperation implements Operation {

    /**
     * Evaluates that the left and right operands of the operation are of the same type.
     * Attempts to find the type of the operands and invokes corresponding implementations.
     *
     * @param left  the operand value.
     * @param right the comparison value.
     * @return the operation result.
     * @throws InvalidOperationException if the values are null, types do not match or an
     *                                   given type is unsupported.
     */
    @Override
    public boolean operate(final Object left, final Object right) {
        if (left == null || right == null) {
            throw new InvalidOperationException("Cannot have null values for operation!");
        }

        if (!left.getClass().equals(right.getClass())) {
            throw new InvalidOperationException("Incompatible values for operation!");
        }

        if (left instanceof String) {
            String strLeft = (String) left;
            String strRight = (String) right;
            if (isNumber(strLeft) && isNumber(strRight)) {
                return operate(toDouble(strLeft), toDouble(strRight));
            }
            return operate(strLeft, strRight);
        } else if (left instanceof Double) {
            return operate((Double) left, (Double) right);
        } else if (left instanceof Integer) {
            return operate(((Integer) left).doubleValue(), ((Integer) right).doubleValue());
        }
        throw new InvalidOperationException("Unsupported type passed to operation!");
    }

    /**
     * Performs operation on {@link String} data types.
     *
     * @param left  the operand value.
     * @param right the comparison value.
     * @return the operation result.
     */
    protected abstract boolean operate(final String left, final String right);

    /**
     * Performs operation on {@link Double} data types.
     *
     * @param left  the operand value.
     * @param right the comparison value.
     * @return the operation result.
     */
    protected abstract boolean operate(final Double left, final Double right);
}
