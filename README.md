# ApigeeRules
Simple Rule Processor Problem
##Design Document
###Models
The rule processor application data are modeled as below:

- Condition : Holds the operand, operator and comparison value
- Action : Holds the operand and value
- Rule : Holds the if condition, if action, else action, priority and insertion sequence
- RuleSet : Holds the rules in the rule set in a priority queue

The Rules in the rule set are ordered using the priority and sequence. If priority is not present for the rule or the priorities are equal, then the insertion order sequence is considered for ordering in the rule set.

###Operations
Operations in conditions are represented using an Operation interface. An abstract implementation of Operation is AbstractOperation which leaves out concrete operation implementation based on the data type as abstraction. Different concrete Operation implementations are:
- EqualToOperation
- NotEqualToOperation
- LesserThanOperation
- LesserThanOrEqualToOperation
- GreaterThanOperation
- GreaterThanOrEqualToOperation

Each of the concrete implementations have the operations implemented for String and Double (numerical) data types.

###Readers
Readers are the components in the application that takes in user input and constructs model objects from the input.
Different reader implementations are :
- RuleSetReader : Reads and constructs RuleSet. Uses a RuleReader to get Rules.
- RuleReader : Abstraction of a rule reader
  - AbstractRuleReader : Abstract implementation using template pattern to create rules. Condition and Action data input is left as abstraction for concrete implementations.
    - RuleValueReader : Concrete implementation which collects Condition and Action data as values and constructs them.
    - RuleExpressionReader : Concrete implementation which collectes Condition and Action data as expression and constructs them.

###Execution
The execution of rules is performed by creating a rule execution sequence of RuleExecutionSteps. This follows a Chain of Responsibility pattern where each step would hold a Rule. The execution chain is built using a RuleExecutionSequenceBuilder. The sequence of rules are collected from the RuleSet.

A rule execution context is maintained to store intermediate operand data as well as rule execution trace. Rules could collect operand value from the context which was populated by previous rules.

####Error Handling
Errors during execution of a rule is handled by RuleExecutionErrorHandler. The default implementation of error handler is DefaultRuleExecutionErrorHandler.

###Helpers
There are helper implementations to collect data or convert data. These are:
- DatatypeConverter : Converts a string value into it's implementation based on it's datatype.
- OperandReader : Attempts to collect operand value from operand map (context) or from user input if not present in the operand map.

###IO
All input-output operations by the application are wrapped in ApplicationIO class. This would help in making an easy transition for the reader or writer later as input and output are used across the application.

###Application Entry
The application entry point is RuleEngineRunner which invokes the RuleEngine implementation. The rule engine implementation would have a parse strategy and execution strategy using the above mentioned design.
