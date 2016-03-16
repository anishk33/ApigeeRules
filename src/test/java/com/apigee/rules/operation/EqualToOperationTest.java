package com.apigee.rules.operation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Anish Kurian.
 */
public class EqualToOperationTest {

    private EqualToOperation equalToOperation;

    @Before
    public void setUp() {
        equalToOperation = new EqualToOperation();
    }

    @Test
    public void testEqualToOperationForNumber() {
        assertTrue(equalToOperation.operate(10, 10));
    }

    @Test
    public void testEqualToOperationForString() {
        assertTrue(equalToOperation.operate("test", "test"));
    }

    @Test
    public void testEqualToOperationForNumberInvalid() {
        assertFalse(equalToOperation.operate(10, 20));
    }

    @Test
    public void testEqualToOperationForStringInvalid() {
        assertFalse(equalToOperation.operate("test1", "test"));
    }

    @Test(expected = InvalidOperationException.class)
    public void testEqualToOperationForNull() {
        Object left = null;
        Object right = null;
        assertTrue(equalToOperation.operate(left, right));
    }

    @Test
    public void testEqualToOperationForStringObjects() {
        Object left = "test";
        Object right = "test";
        assertTrue(equalToOperation.operate(left, right));
    }

    @Test
    public void testEqualToOperationForDecimalObjects() {
        Object left = 10.12;
        Object right = 10.120;
        assertTrue(equalToOperation.operate(left, right));
    }

    @Test
    public void testEqualToOperationForIntegerObjects() {
        Object left = 10;
        Object right = 11;
        assertFalse(equalToOperation.operate(left, right));
    }

}
