package com.apigee.rules.execution;

import com.apigee.rules.exception.InvalidDataException;
import com.apigee.rules.models.Rule;
import com.apigee.rules.models.RuleSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A builder implementation that builds an execution chain.
 * For given rule set the RuleExecutionSequenceBuilder constructs an execution chain.
 * The rule set maintains the order of rules which is used to build the execution chain.
 *
 * @author Anish Kurian.
 */
public class RuleExecutionSequenceBuilder {
    private static final Logger logger = LogManager.getLogger(RuleExecutionSequenceBuilder.class);

    private RuleSet ruleSet;

    public RuleExecutionSequenceBuilder(final RuleSet ruleSet) {
        if (ruleSet == null) {
            throw new InvalidDataException("Cannot build sequence for blank rule set");
        }

        this.ruleSet = ruleSet;
    }

    /**
     * Builds the execution sequence for the rule set and returns the initial execution step.
     * The ordering of rules is maintained in the {@link RuleSet} which is used to build
     * an execution chain containing execution steps.
     *
     * @return the initial execution step.
     */
    public RuleExecutionStep buildExecutionSequence() {
        RuleExecutionStep initialStep = null;
        RuleExecutionStep currentStep;
        RuleExecutionStep previousStep = null;
        RuleExecutionContext ruleExecutionContext = new RuleExecutionContext();

        while (!ruleSet.getRules().isEmpty()) {
            Rule rule = ruleSet.getRules().remove();
            currentStep = new RuleExecutionStep(rule, ruleExecutionContext);

            if (initialStep == null) {
                initialStep = currentStep;
            }

            if (previousStep != null) {
                logger.debug("Setting " + previousStep + " next step as " + currentStep);
                previousStep.setNextStep(currentStep);
            }

            previousStep = currentStep;
        }

        return initialStep;
    }
}
