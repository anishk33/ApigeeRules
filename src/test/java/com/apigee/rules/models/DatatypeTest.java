package com.apigee.rules.models;

import com.apigee.rules.exception.InvalidDataException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Anish Kurian.
 */
public class DatatypeTest {

    @Test
    public void testDatatypeForStringValue() {
        assertEquals(Datatype.STRING, Datatype.getDatatypeForValue("test"));
    }

    @Test
    public void testDatatypeForIntegerValue() {
        assertEquals(Datatype.NUMBER, Datatype.getDatatypeForValue("10"));
    }

    @Test
    public void testDatatypeForDoubleValue() {
        assertEquals(Datatype.NUMBER, Datatype.getDatatypeForValue("34.567"));
    }

    @Test(expected = InvalidDataException.class)
    public void testDatatypeForBlankValue() {
        Datatype.getDatatypeForValue("");
    }

    @Test(expected = InvalidDataException.class)
    public void testDatatypeForNullValue() {
        Datatype.getDatatypeForValue("");
    }
}