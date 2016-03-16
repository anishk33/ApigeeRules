package com.apigee.rules.execution;

import com.apigee.rules.models.Rule;

/**
 * An error handler for handling errors during rule execution.
 * Any clean-up, logging or encapsulation could be handled by the rule execution error handler.
 *
 * @author Anish Kurian.
 */
public interface RuleExecutionErrorHandler {

    /**
     * Handles error during execution of a rule.
     *
     * @param rule      the rule that errored
     * @param exception the cause of error
     */
    void handle(final Rule rule, final Exception exception);
}
