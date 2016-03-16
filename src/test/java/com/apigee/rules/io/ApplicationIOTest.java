package com.apigee.rules.io;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
/**
 * @author Anish Kurian.
 */
public class ApplicationIOTest {

    private static final String message = "testMessage";
    private static final String testValue = "testValue";

    private PrintStream mockPrintStream;
    private Scanner mockScanner;

    private ApplicationIO applicationIO;

    @Before
    public void setUp() {
        mockPrintStream = mock(PrintStream.class);
        mockScanner = new Scanner(new ByteArrayInputStream(testValue.getBytes()));
        applicationIO = new ApplicationIO(mockPrintStream, mockScanner);
    }

    @Test
    public void testPrintMessage() throws Exception {
        applicationIO.printMessage(message);
        verify(mockPrintStream, times(1)).println(message);
    }

    @Test
    public void testGetString() throws Exception {
        assertEquals(testValue, applicationIO.getString());
    }

    @Test
    public void testGetStringMessage() throws Exception {
        assertEquals(testValue, applicationIO.getString(message));
    }

    @Test
    public void testGetInteger() throws Exception {
        String intValue = "100";
        applicationIO = new ApplicationIO(mockPrintStream, new Scanner(new ByteArrayInputStream(intValue.getBytes())));
        assertEquals(Integer.valueOf(intValue), applicationIO.getInteger(message));
    }
}