package com.apigee.rules.helpers;

import com.apigee.rules.exception.InvalidDataException;
import com.apigee.rules.models.Datatype;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Anish Kurian.
 */
public class DatatypeConverterTest {

    @Test
    public void convertStringDatatype() {
        assertTrue(DatatypeConverter.convert("test", Datatype.STRING) instanceof String);
    }

    @Test
    public void convertNumberDatatype() {
        assertTrue(DatatypeConverter.convert("10", Datatype.NUMBER) instanceof Number);
    }

    @Test(expected = InvalidDataException.class)
    public void convertNullDatatype() {
        DatatypeConverter.convert(null, Datatype.STRING);
    }

}