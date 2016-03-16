package com.apigee.rules.operation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Anish Kurian.
 */
public class GreaterThanOperationTest {

    private GreaterThanOperation greaterThanOperation;

    @Before
    public void setUp() {
        greaterThanOperation = new GreaterThanOperation();
    }

    @Test
    public void testGreaterThanOperationForNumber() {
        assertTrue(greaterThanOperation.operate(10, 5));
    }

    @Test
    public void testGreaterThanOperationForString() {
        assertTrue(greaterThanOperation.operate("tested", "test"));
    }

    @Test
    public void testGreaterThanOperationForNumberInvalid() {
        assertFalse(greaterThanOperation.operate(10, 20));
    }

    @Test
    public void testGreaterThanOperationForStringInvalid() {
        assertFalse(greaterThanOperation.operate("test", "test"));
    }

    @Test(expected = InvalidOperationException.class)
    public void testGreaterThanOperationForNull() {
        Object left = null;
        Object right = null;
        assertTrue(greaterThanOperation.operate(left, right));
    }

    @Test
    public void testGreaterThanOperationForStringObjects() {
        Object left = "tested";
        Object right = "test";
        assertTrue(greaterThanOperation.operate(left, right));
    }

    @Test
    public void testGreaterThanOperationForDecimalObjects() {
        Object left = 10.13;
        Object right = 10.120;
        assertTrue(greaterThanOperation.operate(left, right));
    }

    @Test
    public void testGreaterThanOperationForIntegerObjects() {
        Object left = 10;
        Object right = 11;
        assertFalse(greaterThanOperation.operate(left, right));
    }

}