package com.apigee.rules.operation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Anish Kurian.
 */
public class LesserThanOperationTest {

    private LesserThanOperation lesserThanOperation;

    @Before
    public void setUp() {
        lesserThanOperation = new LesserThanOperation();
    }

    @Test
    public void testLesserThanOperationForNumber() {
        assertTrue(lesserThanOperation.operate(5, 10));
    }

    @Test
    public void testLesserThanOperationForString() {
        assertTrue(lesserThanOperation.operate("test", "tested"));
    }

    @Test
    public void testLesserThanOperationForNumberInvalid() {
        assertFalse(lesserThanOperation.operate(20, 10));
    }

    @Test
    public void testLesserThanOperationForStringInvalid() {
        assertFalse(lesserThanOperation.operate("test", "test"));
    }

    @Test(expected = InvalidOperationException.class)
    public void testLesserThanOperationForNull() {
        Object left = null;
        Object right = null;
        assertTrue(lesserThanOperation.operate(left, right));
    }

    @Test
    public void testLesserThanOperationForStringObjects() {
        Object left = "test";
        Object right = "tested";
        assertTrue(lesserThanOperation.operate(left, right));
    }

    @Test
    public void testLesserThanOperationForDecimalObjects() {
        Object left = 10.12;
        Object right = 10.130;
        assertTrue(lesserThanOperation.operate(left, right));
    }

    @Test
    public void testLesserThanOperationForIntegerObjects() {
        Object left = 12;
        Object right = 11;
        assertFalse(lesserThanOperation.operate(left, right));
    }

}