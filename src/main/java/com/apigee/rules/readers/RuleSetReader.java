package com.apigee.rules.readers;

import com.apigee.rules.io.ApplicationIO;
import com.apigee.rules.models.Rule;
import com.apigee.rules.models.RuleSet;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation that reads the rule set from input.
 * Reads multiple rules and builds a rule set using the given rules.
 *
 * @author Anish Kurian.
 */
public class RuleSetReader {

    private RuleReader ruleReader;
    private ApplicationIO applicationIO;

    public RuleSetReader() {
        this(new RuleExpressionReader());
    }

    public RuleSetReader(final RuleReader ruleReader) {
        this(ruleReader, new ApplicationIO());
    }

    public RuleSetReader(final RuleReader ruleReader, final ApplicationIO applicationIO) {
        this.ruleReader = ruleReader;
        this.applicationIO = applicationIO;
    }

    /**
     * Based on user prompt, reads rules and builds a rule set using the given rules.
     *
     * @return the {@link RuleSet}.
     */
    public RuleSet read() {
        applicationIO.printMessage("RuleSet");
        List<Rule> rules = new ArrayList<>();
        while (applicationIO.getString("Enter new rule? (y/n)").equalsIgnoreCase("y")) {
            rules.add(readRule());
        }
        return new RuleSet(rules);
    }

    /**
     * Reads an individual {@link Rule} in the {@link RuleSet}.
     *
     * @return the {@link Rule}.
     */
    private Rule readRule() {
        applicationIO.printMessage("Rule");
        return ruleReader.read();
    }
}
