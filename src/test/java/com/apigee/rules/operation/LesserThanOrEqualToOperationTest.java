package com.apigee.rules.operation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Anish Kurian.
 */
public class LesserThanOrEqualToOperationTest {
    private LesserThanOrEqualToOperation lesserThanOrEqualToOperation;

    @Before
    public void setUp() {
        lesserThanOrEqualToOperation = new LesserThanOrEqualToOperation();
    }

    @Test
    public void testLesserThanOrEqualToOperationForNumber() {
        assertTrue(lesserThanOrEqualToOperation.operate(5, 10));
        assertTrue(lesserThanOrEqualToOperation.operate(10, 10));
    }

    @Test
    public void testLesserThanOrEqualToOperationForString() {
        assertTrue(lesserThanOrEqualToOperation.operate("test", "tested"));
        assertTrue(lesserThanOrEqualToOperation.operate("tested", "tested"));
    }

    @Test
    public void testLesserThanOrEqualToOperationForNumberInvalid() {
        assertFalse(lesserThanOrEqualToOperation.operate(20, 10));
    }

    @Test
    public void testLesserThanOrEqualToOperationForStringInvalid() {
        assertFalse(lesserThanOrEqualToOperation.operate("tested", "test"));
    }

    @Test(expected = InvalidOperationException.class)
    public void testLesserThanOrEqualToOperationForNull() {
        Object left = null;
        Object right = null;
        assertTrue(lesserThanOrEqualToOperation.operate(left, right));
    }

    @Test
    public void testLesserThanOrEqualToOperationForStringObjects() {
        Object left = "test";
        Object right = "tested";
        assertTrue(lesserThanOrEqualToOperation.operate(left, right));

        right = "test";
        assertTrue(lesserThanOrEqualToOperation.operate(left, right));
    }

    @Test
    public void testLesserThanOrEqualToOperationForDecimalObjects() {
        Object left = 10.12;
        Object right = 10.13;
        assertTrue(lesserThanOrEqualToOperation.operate(left, right));

        right = 10.12;
        assertTrue(lesserThanOrEqualToOperation.operate(left, right));
    }

    @Test
    public void testLesserThanOrEqualToOperationForIntegerObjects() {
        Object left = 12;
        Object right = 11;
        assertFalse(lesserThanOrEqualToOperation.operate(left, right));
    }



}