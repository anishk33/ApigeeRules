package com.apigee.rules;

import com.apigee.rules.engine.RuleEngine;
import com.apigee.rules.engine.SimpleRuleEngine;
import com.apigee.rules.io.ApplicationIO;
import com.apigee.rules.models.RuleSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The application entry point.
 *
 * @author Anish Kurian.
 */
public class RuleEngineRunner {
    private static final Logger logger = LogManager.getLogger(RuleEngineRunner.class);

    /**
     * Instantiates a {@link RuleEngine} implementation and performs parse operation to
     * build {@link RuleSet}.
     * If the {@link RuleSet} is empty, does not proceed to rule execution.
     * Else, executes the {@link com.apigee.rules.models.Rule}s in the {@link RuleSet}.
     *
     * @param args command line arguments; are ignored.
     */
    public static void main(String[] args) {
        logger.debug("Running Rule Engine");
        RuleEngine ruleEngine = new SimpleRuleEngine();
        ApplicationIO applicationIO = new ApplicationIO();

        logger.debug("Parsing rule set");
        RuleSet ruleSet = ruleEngine.parse();

        if (ruleSet.getRules().isEmpty()) {
            applicationIO.printMessage("No rules to execute!");
            return;
        }

        logger.debug("Executing rules in rule set");
        ruleEngine.execute(ruleSet);
    }
}
