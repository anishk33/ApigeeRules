package com.apigee.rules.engine;

import com.apigee.rules.models.RuleSet;

/**
 * Interface for rule engine.
 * The Rule Engine is designed to work in 2 phases being:
 * 1. Parsing the rules and building a rule set.
 * 2. Ordered execution of the rules in the rule set.
 *
 * @author Anish Kurian.
 */
public interface RuleEngine {

    /**
     * Parses and builds the rules in a rule set.
     * The conditions and actions will be the input for rules and multiple rules would
     * constitute a rule set.
     *
     * @return {@link RuleSet}
     */
    RuleSet parse();

    /**
     * Builds the execution sequence and executes the rules in the rule set in the execution
     * order.
     *
     * @param ruleSet the {@link RuleSet} to execute.
     */
    void execute(final RuleSet ruleSet);
}
