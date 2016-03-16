package com.apigee.rules.engine;

import com.apigee.rules.execution.RuleExecutionSequenceBuilder;
import com.apigee.rules.execution.RuleExecutionStep;
import com.apigee.rules.models.RuleSet;
import com.apigee.rules.readers.RuleSetReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Default implementation of {@link RuleEngine}.
 *
 * @author Anish Kurian.
 * @see com.apigee.rules.engine.RuleEngine
 */
public class SimpleRuleEngine implements RuleEngine {

    private static final Logger logger = LogManager.getLogger(SimpleRuleEngine.class);

    private RuleSetReader ruleSetReader;

    public SimpleRuleEngine() {
        ruleSetReader = new RuleSetReader();
    }

    /**
     * Uses a {@link RuleSetReader} to read and build the {@link RuleSet}.
     *
     * @see RuleEngine#parse()
     */
    @Override
    public RuleSet parse() {
        return ruleSetReader.read();
    }

    /**
     * Uses a {@link RuleExecutionSequenceBuilder} to build the execution order of the rules in the rule set.
     * The rules are executed as a chain built by {@link RuleExecutionSequenceBuilder}.
     *
     * @param ruleSet the {@link RuleSet} to execute.
     * @see RuleEngine#execute(RuleSet)
     */
    @Override
    public void execute(final RuleSet ruleSet) {
        logger.debug("Building rule execution sequence for ruleset : " + ruleSet);
        RuleExecutionStep ruleExecutionStep = new RuleExecutionSequenceBuilder(ruleSet)
                .buildExecutionSequence();

        if (ruleExecutionStep == null) {
            logger.warn("No rules to execute in rule set!");
            return;
        }

        logger.debug("Initiating rules execution");
        ruleExecutionStep.execute();
    }
}
