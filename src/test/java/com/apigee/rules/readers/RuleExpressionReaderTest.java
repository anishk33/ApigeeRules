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
public class RuleExpressionReaderTest {

    private static final String message = "testMessage";
    private static final String operand = "testOperand";
    private static final String operator = "<=";
    private static final String value = "testValue";

    private ApplicationIO mockApplicationIO;
    private RuleExpressionReader ruleExpressionReader;

    @Before
    public void setUp() {
        mockApplicationIO = mock(ApplicationIO.class);
        ruleExpressionReader = new RuleExpressionReader(mockApplicationIO);
    }

    @Test(expected = DataNotFoundException.class)
    public void testReadInvalidCondition() throws Exception {
        when(mockApplicationIO.getString()).thenReturn(operand).thenReturn("testOperator").thenReturn(value);

        Condition result = ruleExpressionReader.readCondition(message);

        assertEquals(operand, result.getOperand());
        assertEquals(operator, result.getOperator().getValue());
        assertEquals(value, result.getComparisonValue());
    }

    @Test
    public void testReadCondition() throws Exception {
        when(mockApplicationIO.getString()).thenReturn(operand).thenReturn(operator).thenReturn(value);

        Condition result = ruleExpressionReader.readCondition(message);

        assertEquals(operand, result.getOperand());
        assertEquals(operator, result.getOperator().getValue());
        assertEquals(value, result.getComparisonValue());
    }

    @Test
    public void testReadAction() throws Exception {
        when(mockApplicationIO.getString()).thenReturn(operand).thenReturn(value);

        Action result = ruleExpressionReader.readAction(message);

        assertEquals(operand, result.getOperand());
        assertEquals(value, result.getValue());
    }

}