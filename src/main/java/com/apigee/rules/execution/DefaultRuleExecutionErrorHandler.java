package com.apigee.rules.execution;

import com.apigee.rules.io.ApplicationIO;
import com.apigee.rules.models.Rule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Default implementation of {@link RuleExecutionErrorHandler}.
 * This implementation logs the error and prints message regarding the error during the rule execution.
 *
 * @author Anish Kurian.
 * @see com.apigee.rules.execution.RuleExecutionErrorHandler
 */
public class DefaultRuleExecutionErrorHandler implements RuleExecutionErrorHandler {

    private static final Logger logger = LogManager.getLogger(DefaultRuleExecutionErrorHandler.class);
    private static final ApplicationIO applicationIO = new ApplicationIO();

    /**
     * Logs the error and prints the error causing message and the rule that errored out.
     *
     * @param rule      the rule that errored
     * @param exception the cause of error
     * @see RuleExecutionErrorHandler#handle(Rule, Exception)
     */
    @Override
    public void handle(final Rule rule, final Exception exception) {
        logger.error("Error occurred while executing rule : " + rule, exception);
        applicationIO.printMessage("Error executing rule : " + rule);
        applicationIO.printMessage("Error message: " + exception.getMessage());
    }
}
