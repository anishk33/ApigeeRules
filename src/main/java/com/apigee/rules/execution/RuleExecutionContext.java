package com.apigee.rules.execution;

import com.apigee.rules.exception.InvalidDataException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the rule execution context.
 * Holds intermediate execution data and the rule execution trace information.
 *
 * @author Anish Kurian.
 */
public class RuleExecutionContext {

    private Map<String, Object> context;
    private List<String> trace;

    public RuleExecutionContext() {
        context = new HashMap<>();
        trace = new ArrayList<>();
    }

    /**
     * Retrieve a value from the execution context for given key.
     *
     * @param key the key for value to be retrieved.
     * @return the value for given key.
     */
    public Object getValue(final String key) {
        return context.get(key);
    }

    /**
     * Inserts/Updates value for given key in the execution context.
     *
     * @param key   the key for value to be inserted/updated.
     * @param value the value to be inserted.
     * @throws InvalidDataException if key is blank.
     */
    public void addValue(final String key, final Object value) {
        if (StringUtils.isBlank(key)) {
            throw new InvalidDataException("Cannot insert into context with key " + key);
        }

        context.put(key, value);
    }

    /**
     * Adds a new trace into the execution traces.
     * The order of traces are maintained.
     *
     * @param traceValue the trace value to be added.
     */
    public void addTrace(final String traceValue) {
        trace.add(traceValue);
    }

    /**
     * Retrieves a copy of the current state of execution context.
     *
     * @return copy of the execution context.
     */
    public Map<String, Object> getContext() {
        return new HashMap(context);
    }

    /**
     * Retrieves a copy of the current state of execution traces.
     *
     * @return copy of the execution traces.
     */
    public List<String> getTrace() {
        return new ArrayList(trace);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("context", context)
                .append("trace", trace)
                .toString();
    }
}
