package com.apigee.rules.execution;

import com.apigee.rules.exception.InvalidDataException;
import com.apigee.rules.io.ApplicationIO;
import com.apigee.rules.models.Rule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents an individual rule execution step.
 * Uses a {@link RuleExecutor} to execute the current rule and uses the {@link RuleExecutionContext}
 * for intermediate execution data usage and trace information.
 *
 * @author Anish Kurian.
 */
public class RuleExecutionStep {

    private static final Logger logger = LogManager.getLogger(RuleExecutionStep.class);

    private RuleExecutionStep nextStep;
    private RuleExecutionContext ruleExecutionContext;
    private Rule rule;
    private RuleExecutor ruleExecutor;
    private ApplicationIO applicationIO;
    private RuleExecutionErrorHandler ruleExecutionErrorHandler;

    public RuleExecutionStep(final Rule rule) {
        this(rule, new RuleExecutionContext());
    }

    public RuleExecutionStep(final Rule rule, final RuleExecutionContext ruleExecutionContext) {
        if (rule == null) {
            throw new InvalidDataException("Cannot have an empty rule in execution sequence");
        }

        this.rule = rule;
        this.ruleExecutionContext = ruleExecutionContext;
        this.ruleExecutor = new RuleExecutor();
        this.applicationIO = new ApplicationIO();
        this.ruleExecutionErrorHandler = new DefaultRuleExecutionErrorHandler();
    }

    /**
     * Executes the rule in the current execution step and then invokes the next execution step.
     * On reaching the end of the chain, prints the result and rule execution trace information.
     * <p/>
     * In case of Exception, {@link RuleExecutionErrorHandler} is invoked for handling errors.
     */
    public void execute() {
        logger.debug("Executing rule : " + rule + ", context: " + ruleExecutionContext);

        Object result = null;

        try {
            result = ruleExecutor.execute(rule, ruleExecutionContext);
        } catch (Exception e) {
            ruleExecutionErrorHandler.handle(rule, e);
        }

        if (nextStep != null) {
            logger.debug("Executing next step");
            nextStep.execute();
        } else {
            applicationIO.printMessage("Result value : " + result);
            applicationIO.printMessage("Rules Execution Trace");
            for (String trace : ruleExecutionContext.getTrace()) {
                applicationIO.printMessage(trace);
            }
        }
    }

    /**
     * Set the next step in the rule execution chain.
     *
     * @param nextStep the next step in the execution chain.
     */
    public void setNextStep(final RuleExecutionStep nextStep) {
        this.nextStep = nextStep;
    }
}
