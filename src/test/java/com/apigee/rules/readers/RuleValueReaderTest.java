package com.apigee.rules.readers;

import com.apigee.rules.exception.DataNotFoundException;
import com.apigee.rules.io.ApplicationIO;
import com.apigee.rules.models.Action;
import com.apigee.rules.models.Condition;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Anish Kurian.
 */
public class RuleValueReaderTest {

    private static final String message = "testMessage";
    private static final String operand = "testOperand";
    private static final String operator = "<=";
    private static final String value = "testValue";

    private ApplicationIO mockApplicationIO;
    private RuleValueReader ruleValueReader;

    @Before
    public void setUp() {
        mockApplicationIO = mock(ApplicationIO.class);
        ruleValueReader = new RuleValueReader(mockApplicationIO);
    }

    @Test(expected = DataNotFoundException.class)
    public void testReadInvalidCondition() throws Exception {
        when(mockApplicationIO.getString("Enter operand: ")).thenReturn(operand);
        when(mockApplicationIO.getString("Enter operator: ")).thenReturn("testOperator");
        when(mockApplicationIO.getString("Enter comparison value: ")).thenReturn(value);

        Condition result = ruleValueReader.readCondition(message);

        assertEquals(operand, result.getOperand());
        assertEquals(operator, result.getOperator().getValue());
        assertEquals(value, result.getComparisonValue());
    }

    @Test
    public void testReadCondition() throws Exception {
        when(mockApplicationIO.getString("Enter operand: ")).thenReturn(operand);
        when(mockApplicationIO.getString("Enter operator: ")).thenReturn(operator);
        when(mockApplicationIO.getString("Enter comparison value: ")).thenReturn(value);

        Condition result = ruleValueReader.readCondition(message);

        assertEquals(operand, result.getOperand());
        assertEquals(operator, result.getOperator().getValue());
        assertEquals(value, result.getComparisonValue());
    }

    @Test
    public void testReadAction() throws Exception {
        when(mockApplicationIO.getString("Enter operand: ")).thenReturn(operand);
        when(mockApplicationIO.getString("Enter value: ")).thenReturn(value);

        Action result = ruleValueReader.readAction(message);

        assertEquals(operand, result.getOperand());
        assertEquals(value, result.getValue());
    }
}