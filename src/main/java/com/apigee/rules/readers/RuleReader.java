package com.apigee.rules.readers;

import com.apigee.rules.models.Rule;

/**
 * Represents a reader for reading, parsing and constructing rules.
 *
 * @author Anish Kurian.
 */
public interface RuleReader {

    /**
     * Reads the rule from input stream.
     *
     * @return the {@link Rule} read.
     */
    Rule read();
}
