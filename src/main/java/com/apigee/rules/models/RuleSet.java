package com.apigee.rules.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;
import java.util.PriorityQueue;

/**
 * Model representing a Rule Set.
 * Holds rules in a priority queue which is sorted based on {@link Rule#priority} and {@link Rule#sequence}.
 *
 * @author Anish Kurian.
 */
public class RuleSet {
    PriorityQueue<Rule> rules;

    public RuleSet(final Collection rules) {
        this.rules = new PriorityQueue<>(rules);
    }

    public PriorityQueue<Rule> getRules() {
        return rules;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("rules", rules)
                .toString();
    }
}
