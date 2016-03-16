package com.apigee.rules.readers;

import com.apigee.rules.io.ApplicationIO;
import com.apigee.rules.models.Action;
import com.apigee.rules.models.Condition;
import com.apigee.rules.models.Rule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An abstract implementation of {@link RuleReader}.
 * Follows template pattern where the algorithm for rule reading is defined in the abstraction.
 * Concrete implementations are responsible for the implementation of the steps.
 *
 * @author Anish Kurian.
 * @see com.apigee.rules.readers.RuleReader
 */
public abstract class RuleAbstractReader implements RuleReader {

    protected static final Logger logger = LogManager.getLogger(RuleAbstractReader.class);

    protected ApplicationIO applicationIO;

    /**
     * Reads the if condition, if action, else action and priority from input stream.
     * Builds the condition and actions and uses it along with priority to build the {@link Rule}.
     *
     * @return the {@link Rule} built.
     * @see {@link RuleReader#read()}
     */
    public Rule read() {
        Condition ifCondition = readCondition();
        Action ifAction = readIfAction();
        Action elseAction = readElseAction();
        Integer priority = readPriority();
        return new Rule(ifCondition, ifAction, elseAction, priority);
    }

    /**
     * Reads the condition from input. If the input errors, a re-attempt for input is done.
     * Builds the condition from the input.
     *
     * @return the {@link Condition}
     */
    private Condition readCondition() {
        Condition ifCondition = null;
        while (ifCondition == null) {
            try {
                ifCondition = readCondition("If Condition");
            } catch (Exception e) {
                logger.warn("Failed to read if condition", e);
                applicationIO.printMessage(e.getMessage());
            }
        }
        return ifCondition;
    }

    /**
     * Reads the if action from input. If the input errors, a re-attempt for input is done.
     * Builds the if action from the input.
     *
     * @return the {@link Action}.
     */
    private Action readIfAction() {
        Action ifAction = null;
        while (ifAction == null) {
            try {
                ifAction = readAction("If Action");
            } catch (Exception e) {
                logger.warn("Failed to read if action", e);
                applicationIO.printMessage(e.getMessage());
            }
        }
        return ifAction;
    }

    /**
     * Reads the else action from input. If the input errors, a re-attempt for input is made.
     * Builds the else action from input.
     *
     * @return the {@link Action}.
     */
    private Action readElseAction() {
        Action elseAction = null;
        while (elseAction == null) {
            try {
                elseAction = readAction("Else Action");
            } catch (Exception e) {
                logger.error("Failed to read else action", e);
                applicationIO.printMessage(e.getMessage());
            }
        }
        return elseAction;
    }

    /**
     * Reads the priority from input. If the input errors, a re-attempt for input is made.
     * Builds the priority from input.
     *
     * @return the priority.
     */
    private Integer readPriority() {
        Integer priority = null;
        while (priority == null) {
            try {
                priority = readPriority("Priority");
            } catch (Exception e) {
                logger.warn("Failed to read priority", e);
                applicationIO.printMessage(e.getMessage());
            }
        }
        return priority < 0 ? null : priority;
    }

    /**
     * Reads the condition from input after printing the given message.
     * The condition is parsed and constructed as object.
     *
     * @param message the message to be printed.
     * @return the {@link Condition}
     */
    protected abstract Condition readCondition(final String message);

    /**
     * Reads the action from input after printing the given message.
     * The action is parsed and constructed as object.
     *
     * @param message the message to be printed.
     * @return the {@link Action}.
     */
    protected abstract Action readAction(final String message);

    /**
     * Reads the priority from input after printing the given message.
     *
     * @param message the message to be printed.
     * @return the priority.
     */
    protected Integer readPriority(final String message) {
        applicationIO.printMessage(message);
        String stringValue = applicationIO.getString("Enter priority (-1 for none): ");
        return Integer.parseInt(stringValue);
    }
}
