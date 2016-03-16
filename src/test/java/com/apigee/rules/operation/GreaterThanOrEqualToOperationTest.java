package com.apigee.rules.operation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Anish Kurian.
 */
public class GreaterThanOrEqualToOperationTest {
    private GreaterThanOrEqualToOperation greaterThanOrEqualToOperation;

    @Before
    public void setUp() {
        greaterThanOrEqualToOperation = new GreaterThanOrEqualToOperation();
    }

    @Test
    public void testGreaterThanOrEqualToOperationForNumber() {
        assertTrue(greaterThanOrEqualToOperation.operate(10, 5));
        assertTrue(greaterThanOrEqualToOperation.operate(10, 10));
    }

    @Test
    public void testGreaterThanOrEqualToOperationForString() {
        assertTrue(greaterThanOrEqualToOperation.operate("tested", "test"));
        assertTrue(greaterThanOrEqualToOperation.operate("tested", "tested"));
    }

    @Test
    public void testGreaterThanOrEqualToOperationForNumberInvalid() {
        assertFalse(greaterThanOrEqualToOperation.operate(10, 20));
    }

    @Test
    public void testGreaterThanOrEqualToOperationForStringInvalid() {
        assertFalse(greaterThanOrEqualToOperation.operate("test", "tested"));
    }

    @Test(expected = InvalidOperationException.class)
    public void testGreaterThanOrEqualToOperationForNull() {
        Object left = null;
        Object right = null;
        assertTrue(greaterThanOrEqualToOperation.operate(left, right));
    }

    @Test
    public void testGreaterThanOrEqualToOperationForStringObjects() {
        Object left = "tested";
        Object right = "test";
        assertTrue(greaterThanOrEqualToOperation.operate(left, right));

        right = "tested";
        assertTrue(greaterThanOrEqualToOperation.operate(left, right));
    }

    @Test
    public void testGreaterThanOrEqualToOperationForDecimalObjects() {
        Object left = 10.13;
        Object right = 10.120;
        assertTrue(greaterThanOrEqualToOperation.operate(left, right));

        right = 10.13;
        assertTrue(greaterThanOrEqualToOperation.operate(left, right));
    }

    @Test
    public void testGreaterThanOrEqualToOperationForIntegerObjects() {
        Object left = 10;
        Object right = 11;
        assertFalse(greaterThanOrEqualToOperation.operate(left, right));
    }


}