package com.apigee.rules.readers;

import com.apigee.rules.io.ApplicationIO;
import com.apigee.rules.models.Rule;
import com.apigee.rules.models.RuleSet;
import org.junit.Before;
import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Anish Kurian.
 */
public class RuleSetReaderTest {

    private Rule mockRule;
    private RuleReader mockRuleReader;
    private ApplicationIO mockApplicationIO;
    private RuleSetReader ruleSetReader;

    @Before
    public void setUp() {
        mockRule = mock(Rule.class);
        mockRuleReader = mock(RuleReader.class);
        mockApplicationIO = mock(ApplicationIO.class);
        ruleSetReader = new RuleSetReader(mockRuleReader, mockApplicationIO);
    }

    @Test
    public void testRead() {
        when(mockApplicationIO.getString(anyString())).thenReturn("y").thenReturn("n");
        when(mockRuleReader.read()).thenReturn(mockRule);
        RuleSet ruleSet = ruleSetReader.read();
        PriorityQueue rules = ruleSet.getRules();
        assertEquals(1, rules.size());
        assertEquals(mockRule, rules.remove());

        verify(mockApplicationIO, times(2)).getString(anyString());
        verify(mockRuleReader, times(1)).read();
    }

}