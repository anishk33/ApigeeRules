package com.apigee.rules.helpers;

import com.apigee.rules.io.ApplicationIO;
import com.apigee.rules.models.Datatype;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Anish Kurian.
 */
public class OperandReaderTest {

    private static final String key = "testKey";

    private ApplicationIO mockApplicationIO;
    private Object valueObject;
    private OperandReader operandReader;

    @Before
    public void setUp() {
        mockApplicationIO = mock(ApplicationIO.class);
        Map<String, Object> map = new HashMap<String, Object>();
        valueObject = new Object();
        map.put(key, valueObject);
        operandReader = new OperandReader(map, mockApplicationIO);
    }

    @Test
    public void testReadFromMap() {
        Object result = operandReader.read(key);
        assertEquals(valueObject, result);
    }

    @Test
    public void testReadFromSystemIO() {
        String strValue = "100";
        Object value = DatatypeConverter.convert(strValue, Datatype.getDatatypeForValue(strValue));
        when(mockApplicationIO.getString(anyString())).thenReturn(strValue);
        Object result = operandReader.read("testKey2");
        assertEquals(value, result);
    }

}