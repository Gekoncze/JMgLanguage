package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class BinaryOperatorExpressionFilter extends AbstractFilter<MgFunction> {
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

    public BinaryOperatorExpressionFilter(
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
    protected boolean filter(MgComponent component) {
        if(component instanceof MgFunction){
            MgFunction function = (MgFunction) component;
            if(function.getOperator() != null){
                MgOperator operator = function.getOperator();
                if(operator.getName().equals(name)){
                    return filterFunction(function);
                }
            }
        }
        return false;
    }

    private boolean filterFunction(MgFunction function){
        if(parentInputInterface != null){
            // binary operator function must have exactly one output value
            if(function.getOutput().count() != 1) return false;
            MgDatatype parentInputDatatype = parentInputInterface.get(replication).getRequestedDatatype();
            MgDatatype functionOutputDatatype = function.getOutput().getFirst().getDatatype();
            if(!Matcher.matches(parentInputDatatype, functionOutputDatatype)) return false;
        }

        if(leftChildrenOutputInterface != null && rightChildrenOutputInterface != null){
            // binary operator function must have exactly two input values
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
        return "Could not find matching operator " + name + ".";
    }

    @Override
    protected String ambiguousMessage() {
        return "Operator " + name + " is ambiguous.";
    }
}
