package com.apigee.rules.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Anish Kurian.
 */
public class OperatorTest {

    @Test
    public void testEqualToInteger() {
        assertTrue(Operator.EQUAL_TO.operate(10, 10));
    }

    @Test
    public void testEqualToDouble() {
        assertTrue(Operator.EQUAL_TO.operate(10.5, 10.5));
    }

    @Test
    public void testEqualToString() {
        assertTrue(Operator.EQUAL_TO.operate("test", "test"));
    }

    @Test
    public void testEqualToIntegerInvalid() {
        assertFalse(Operator.EQUAL_TO.operate(10, 11));
    }

    @Test
    public void testEqualToDoubleInvalid() {
        assertFalse(Operator.EQUAL_TO.operate(10.5, 11.5));
    }

    @Test
    public void testEqualToStringInvalid() {
        assertFalse(Operator.EQUAL_TO.operate("test", "test1"));
    }

    @Test
    public void testLesserThanInteger() {
        assertTrue(Operator.LESSER_THAN.operate(8, 10));
    }

    @Test
    public void testLesserThanDouble() {
        assertTrue(Operator.LESSER_THAN.operate(3.5, 10.5));
    }

    @Test
    public void testLesserThanString() {
        assertTrue(Operator.LESSER_THAN.operate("test", "tested"));
    }

    @Test
    public void testLesserThanIntegerInvalid() {
        assertFalse(Operator.LESSER_THAN.operate(20, 11));
    }

    @Test
    public void testLesserThanDoubleInvalid() {
        assertFalse(Operator.LESSER_THAN.operate(15.5, 11.5));
    }

    @Test
    public void testLesserThanStringInvalid() {
        assertFalse(Operator.LESSER_THAN.operate("tested", "test1"));
    }

    @Test
    public void testGreaterThanInteger() {
        assertTrue(Operator.GREATER_THAN.operate(18, 10));
    }

    @Test
    public void testGreaterThanDouble() {
        assertTrue(Operator.GREATER_THAN.operate(13.5, 10.5));
    }

    @Test
    public void testGreaterThanString() {
        assertTrue(Operator.GREATER_THAN.operate("tested", "test"));
    }

    @Test
    public void testGreaterThanIntegerInvalid() {
        assertFalse(Operator.GREATER_THAN.operate(20, 31));
    }

    @Test
    public void testGreaterThanDoubleInvalid() {
        assertFalse(Operator.GREATER_THAN.operate(15.5, 21.5));
    }

    @Test
    public void testGreaterThanStringInvalid() {
        assertFalse(Operator.GREATER_THAN.operate("test", "test1"));
    }

    @Test
    public void testNotEqualToInteger() {
        assertTrue(Operator.NOT_EQUAL_TO.operate(8, 10));
    }

    @Test
    public void testNotEqualToDouble() {
        assertTrue(Operator.NOT_EQUAL_TO.operate(10.5, 12.5));
    }

    @Test
    public void testNotEqualToString() {
        assertTrue(Operator.NOT_EQUAL_TO.operate("test", "tested"));
    }

    @Test
    public void testNotEqualToIntegerInvalid() {
        assertFalse(Operator.NOT_EQUAL_TO.operate(10, 10));
    }

    @Test
    public void testNotEqualToDoubleInvalid() {
        assertFalse(Operator.NOT_EQUAL_TO.operate(10.5, 10.5));
    }

    @Test
    public void testNotEqualToStringInvalid() {
        assertFalse(Operator.NOT_EQUAL_TO.operate("test", "test"));
    }

    @Test
    public void testLesserThanOrEqualToInteger() {
        assertTrue(Operator.LESSER_THAN_OR_EQUAL.operate(8, 10));
        assertTrue(Operator.LESSER_THAN_OR_EQUAL.operate(8, 8));
    }

    @Test
    public void testLesserThanOrEqualToDouble() {
        assertTrue(Operator.LESSER_THAN_OR_EQUAL.operate(3.5, 10.5));
        assertTrue(Operator.LESSER_THAN_OR_EQUAL.operate(3.5, 3.5));
    }

    @Test
    public void testLesserThanOrEqualToString() {
        assertTrue(Operator.LESSER_THAN_OR_EQUAL.operate("test", "tested"));
        assertTrue(Operator.LESSER_THAN_OR_EQUAL.operate("test", "test"));
    }

    @Test
    public void testLesserThanOrEqualToIntegerInvalid() {
        assertFalse(Operator.LESSER_THAN_OR_EQUAL.operate(20, 11));
    }

    @Test
    public void testLesserThanOrEqualToDoubleInvalid() {
        assertFalse(Operator.LESSER_THAN_OR_EQUAL.operate(15.5, 11.5));
    }

    @Test
    public void testLesserThanOrEqualToStringInvalid() {
        assertFalse(Operator.LESSER_THAN_OR_EQUAL.operate("tested", "test1"));
    }

    @Test
    public void testGreaterThanOrEqualToInteger() {
        assertTrue(Operator.GREATER_THAN_OR_EQUAL.operate(18, 10));
        assertTrue(Operator.GREATER_THAN_OR_EQUAL.operate(18, 18));
    }

    @Test
    public void testGreaterThanOrEqualToDouble() {
        assertTrue(Operator.GREATER_THAN_OR_EQUAL.operate(13.5, 10.5));
        assertTrue(Operator.GREATER_THAN_OR_EQUAL.operate(13.5, 13.5));
    }

    @Test
    public void testGreaterThanOrEqualToString() {
        assertTrue(Operator.GREATER_THAN_OR_EQUAL.operate("tested", "test"));
        assertTrue(Operator.GREATER_THAN_OR_EQUAL.operate("tested", "tested"));
    }

    @Test
    public void testGreaterThanOrEqualToIntegerInvalid() {
        assertFalse(Operator.GREATER_THAN_OR_EQUAL.operate(20, 31));
    }

    @Test
    public void testGreaterThanOrEqualToDoubleInvalid() {
        assertFalse(Operator.GREATER_THAN_OR_EQUAL.operate(15.5, 21.5));
    }

    @Test
    public void testGreaterThanOrEqualToStringInvalid() {
        assertFalse(Operator.GREATER_THAN_OR_EQUAL.operate("test", "test1"));
    }
}