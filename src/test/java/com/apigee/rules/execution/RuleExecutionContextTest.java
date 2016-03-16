package com.apigee.rules.execution;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Anish Kurian.
 */
public class RuleExecutionContextTest {

    private static final String key = "testKey";
    private static final String value = "testValue";

    private RuleExecutionContext ruleExecutionContext;

    @Before
    public void setUp() {
        ruleExecutionContext = new RuleExecutionContext();
    }

    @Test
    public void testGetValue() throws Exception {
        ruleExecutionContext.addValue(key, value);
        Object result = ruleExecutionContext.getValue(key);
        assertEquals(value, result);
    }

    @Test
    public void testAddValue() throws Exception {
        assertEquals(0, ruleExecutionContext.getContext().size());
        ruleExecutionContext.addValue(key, value);
        assertEquals(1, ruleExecutionContext.getContext().size());
    }

    @Test
    public void testGetContext() throws Exception {
        ruleExecutionContext.addValue(key, value);
        assertEquals(1, ruleExecutionContext.getContext().size());
    }
}