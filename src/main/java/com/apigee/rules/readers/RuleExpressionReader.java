package com.apigee.rules.readers;

import com.apigee.rules.helpers.DatatypeConverter;
import com.apigee.rules.io.ApplicationIO;
import com.apigee.rules.models.Action;
import com.apigee.rules.models.Condition;
import com.apigee.rules.models.Datatype;
import com.apigee.rules.models.Operator;

/**
 * A concrete implementation of {@link RuleReader} which parses the conditions and actions
 * as expressions.
 *
 * @author Anish Kurian.
 * @see com.apigee.rules.readers.RuleAbstractReader
 */
public class RuleExpressionReader extends RuleAbstractReader {

    public RuleExpressionReader() {
        this(new ApplicationIO());
    }

    public RuleExpressionReader(final ApplicationIO applicationIO) {
        this.applicationIO = applicationIO;
    }

    /**
     * Reads the operand, operator and value as space separated values.
     *
     * @param message the message to be printed.
     * @return {@link Condition}.
     * @see RuleAbstractReader#readCondition()
     */
    protected Condition readCondition(final String message) {
        applicationIO.printMessage(message);
        applicationIO.printMessage("(<operand> <operator> <comp value>)");

        String operand = applicationIO.getString();
        String operator = applicationIO.getString();
        String value = applicationIO.getString();

        Datatype datatype = Datatype.getDatatypeForValue(value);
        Object comparisonValue = DatatypeConverter.convert(value, datatype);

        return new Condition(operand, Operator.fromValue(operator), datatype, comparisonValue);
    }

    /**
     * Reads the operand and value as space separated values.
     *
     * @param message the message to be printed.
     * @return {@link Action}.
     * @see RuleAbstractReader#readAction(String)
     */
    protected Action readAction(final String message) {
        applicationIO.printMessage(message);
        applicationIO.printMessage("<operand> <value>");

        String operand = applicationIO.getString();
        String value = applicationIO.getString();

        Datatype datatype = Datatype.getDatatypeForValue(value);
        return new Action(operand, datatype, DatatypeConverter.convert(value, datatype));
    }
}