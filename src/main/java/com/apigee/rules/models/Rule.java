package com.apigee.rules.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Model representing a Rule.
 * Holds an if condition, if action, else action, priority and sequence number.
 *
 * @author Anish Kurian.
 */
public class Rule implements Comparable<Rule> {
    private Condition ifCondition;
    private Action ifAction;
    private Action elseAction;
    private Integer priority;
    private long sequence;

    public Rule(final Condition ifCondition, final Action ifAction,
                final Action elseAction, final Integer priority) {
        this.ifCondition = ifCondition;
        this.ifAction = ifAction;
        this.elseAction = elseAction;
        this.priority = priority;
        this.sequence = System.currentTimeMillis();
    }

    public Rule(final Condition ifCondition, final Action ifAction, final Action elseAction) {
        this(ifCondition, ifAction, elseAction, null);
    }

    public Condition getIfCondition() {
        return ifCondition;
    }

    public Action getIfAction() {
        return ifAction;
    }

    public Action getElseAction() {
        return elseAction;
    }

    public Integer getPriority() {
        return priority;
    }

    /**
     * The sorting order is based on priority of the rules.
     * If there are no priorities or the priorities are equal, the sorting order is based on sequence.
     *
     * @param other other {@link Rule} being compared with.
     * @return the order
     */
    @Override
    public int compareTo(Rule other) {
        if (this.priority != other.priority) {
            return this.priority - other.priority;
        }
        return this.sequence < other.sequence ? -1 : 1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ifCondition", ifCondition)
                .append("ifAction", ifAction)
                .append("elseAction", elseAction)
                .append("priority", priority)
                .append("sequence", sequence)
                .toString();
    }
}
