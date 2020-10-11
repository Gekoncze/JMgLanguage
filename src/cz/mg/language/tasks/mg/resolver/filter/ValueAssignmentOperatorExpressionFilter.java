package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class ValueAssignmentOperatorExpressionFilter extends Filter<MgFunction> {
    @Mandatory @Value
    private final ReadableText name;

    @Optional @Link
    private final ReadableArray<InputConnector> parentInputInterface;

    @Optional @Link
    private final ReadableArray<OutputConnector> leftChildrenOutputInterface;

    @Optional @Link
    private final ReadableArray<OutputConnector> rightChildrenOutputInterface;

    @Mandatory @Value
    private final int replication;

    public ValueAssignmentOperatorExpressionFilter(
        Context context,
        ReadableText name,
        InputInterface parentInputInterface,
        OutputInterface leftChildrenOutputInterface,
        OutputInterface rightChildrenOutputInterface,
        int replication
    ) {
        super(context);
        this.name = name;
        this.parentInputInterface = parentInputInterface != null ? parentInputInterface.getRemainingConnectors() : null;
        this.leftChildrenOutputInterface = leftChildrenOutputInterface != null ? leftChildrenOutputInterface.getConnectors() : null;
        this.rightChildrenOutputInterface = rightChildrenOutputInterface != null ? rightChildrenOutputInterface.getConnectors() : null;
        this.replication = replication;
    }

    @Override
    protected boolean filter(MgObject object, ReadableText localName) {
        if(object instanceof MgOperator){
            MgOperator operator = (MgOperator) object;
            if(operator.getInfo() != null){
                if(operator.getInfo().getName().equals(name)){
                    return filterFunction(operator);
                }
            }
        }
        return false;
    }

    private boolean filterFunction(MgFunction function){
        if(parentInputInterface != null){
            // value assignment function must have no output value
            if(function.getOutput().count() > 0) return false;
        }

        if(leftChildrenOutputInterface != null && rightChildrenOutputInterface != null){
            // value assignment function must have exactly two input values
            if(function.getInput().count() != 2) return false;
            MgDatatype childrenOutputDatatypeLeft = leftChildrenOutputInterface.get(replication).getRequestedDatatype();
            MgDatatype childrenOutputDatatypeRight = rightChildrenOutputInterface.get(replication).getRequestedDatatype();
            MgDatatype functionInputDatatypeLeft = function.getOutput().getFirst().getDatatype();
            MgDatatype functionInputDatatypeRight = function.getOutput().getLast().getDatatype();
            if(!Matcher.matches(functionInputDatatypeLeft, childrenOutputDatatypeLeft)) return false;
            if(!Matcher.matches(functionInputDatatypeRight, childrenOutputDatatypeRight)) return false;
        }

        return true;
    }

    @Override
    protected String notFoundMessage() {
        return "Could not find matching value assignment function " + name + ".";
    }

    @Override
    protected String ambiguousMessage() {
        return "Value assignment function " + name + " is ambiguous.";
    }
}
