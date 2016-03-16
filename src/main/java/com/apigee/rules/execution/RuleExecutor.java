package com.apigee.rules.execution;

import com.apigee.rules.helpers.OperandReader;
import com.apigee.rules.models.Action;
import com.apigee.rules.models.Condition;
import com.apigee.rules.models.Rule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A rule executor implementation.
 * Executes given rule using the data in rule execution context.
 * The {@link RuleExecutionContext} may be updated with intermediate data and execution trace.
 *
 * @author Anish Kurian.
 */
public class RuleExecutor {

    private static final Logger logger = LogManager.getLogger(RuleExecutor.class);

    /**
     * Executes given rule by evaluating the condition. The resultant action is chosen based on the
     * evaluation result.
     * {@link OperandReader} is used to read the operand value. The operand value is chosen from
     * {@link RuleExecutionContext#context} or as input.
     *
     * @param rule                 the rule to be executed.
     * @param ruleExecutionContext the rule execution context.
     * @return the result value.
     */
    public Object execute(final Rule rule, final RuleExecutionContext ruleExecutionContext) {
        logger.debug("Rule execution context : " + ruleExecutionContext);

        OperandReader operandReader = new OperandReader(ruleExecutionContext.getContext());
        Condition ifCondition = rule.getIfCondition();
        Object operandValue = operandReader.read(ifCondition.getOperand());
        ruleExecutionContext.addValue(ifCondition.getOperand(), operandValue);

        logger.debug("Operand value for operand " + ifCondition.getOperand() + " is " + operandValue);

        boolean ifConditionResult = ifCondition.getOperator()
                .operate(operandValue, ifCondition.getComparisonValue());

        logger.debug("If condition result : " + ifConditionResult);

        Action resultAction = rule.getElseAction();
        if (ifConditionResult) {
            resultAction = rule.getIfAction();
        }

        logger.debug("Result action : " + resultAction);

        ruleExecutionContext.addTrace(buildExecutionTrace(ifCondition, resultAction));
        ruleExecutionContext.addValue(resultAction.getOperand(), resultAction.getValue());

        return resultAction.getValue();
    }

    /**
     * Builds the execution trace message from evaluated condition and result action.
     *
     * @param condition the evaluated condition.
     * @param action    the result action.
     * @return the execution trace message.
     */
    private String buildExecutionTrace(final Condition condition, final Action action) {
        StringBuilder executionTrace = new StringBuilder();
        executionTrace.append(condition.getOperand()).append(" ")
                .append(condition.getOperator().getValue()).append(" ")
                .append(condition.getComparisonValue()).append(" => ")
                .append(action.getOperand()).append(" = ")
                .append(action.getValue());
        return executionTrace.toString();
    }
}
