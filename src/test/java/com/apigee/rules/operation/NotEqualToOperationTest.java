package com.apigee.rules.operation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Anish Kurian.
 */
public class NotEqualToOperationTest {

    private NotEqualToOperation notEqualToOperation;

    @Before
    public void setUp() {
        notEqualToOperation = new NotEqualToOperation();
    }

    @Test
    public void testNotEqualToOperationForNumber() {
        assertTrue(notEqualToOperation.operate(10, 20));
    }

    @Test
    public void testNotEqualToOperationForString() {
        assertTrue(notEqualToOperation.operate("test", "test1"));
    }

    @Test
    public void testNotEqualToOperationForNumberInvalid() {
        assertFalse(notEqualToOperation.operate(10, 10));
    }

    @Test
    public void testNotEqualToOperationForStringInvalid() {
        assertFalse(notEqualToOperation.operate("test", "test"));
    }

    @Test(expected = InvalidOperationException.class)
    public void testNotEqualToOperationForNull() {
        Object left = null;
        Object right = null;
        assertTrue(notEqualToOperation.operate(left, right));
    }

    @Test
    public void testNotEqualToOperationForStringObjects() {
        Object left = "test";
        Object right = "test1";
        assertTrue(notEqualToOperation.operate(left, right));
    }

    @Test
    public void testNotEqualToOperationForDecimalObjects() {
        Object left = 10.12;
        Object right = 10.13;
        assertTrue(notEqualToOperation.operate(left, right));
    }

    @Test
    public void testNotEqualToOperationForIntegerObjects() {
        Object left = 10;
        Object right = 10;
        assertFalse(notEqualToOperation.operate(left, right));
    }

}