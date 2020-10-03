package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.components.types.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class UnaryOperatorExpressionFilter extends Filter<MgFunction> {
    @Mandatory @Value
    private final ReadableText name;

    @Optional @Link
    private final ReadableArray<InputConnector> parentInputInterface;

    @Optional @Link
    private final ReadableArray<OutputConnector> childrenOutputInterface;

    @Mandatory @Value
    private final int replication;

    public UnaryOperatorExpressionFilter(
        Context context,
        ReadableText name,
        InputInterface parentInputInterface,
        OutputInterface childrenOutputInterface,
        int replication
    ) {
        super(context);
        this.name = name;
        this.parentInputInterface = parentInputInterface != null ? parentInputInterface.getRemainingConnectors() : null;
        this.childrenOutputInterface = childrenOutputInterface != null ? childrenOutputInterface.getConnectors() : null;
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
            // unary operator function must have exactly one output value
            if(function.getOutput().count() != 1) return false;
            MgDatatype parentInputDatatype = parentInputInterface.get(replication).getRequestedDatatype();
            MgDatatype functionOutputDatatype = function.getOutput().getFirst().getDatatype();
            if(!Matcher.matches(parentInputDatatype, functionOutputDatatype)) return false;
        }

        if(childrenOutputInterface != null){
            // unary operator function must have exactly one input value
            if(function.getInput().count() != 1) return false;
            MgDatatype childrenOutputDatatype = childrenOutputInterface.get(replication).getRequestedDatatype();
            MgDatatype functionInputDatatype = function.getOutput().getFirst().getDatatype();
            if(!Matcher.matches(functionInputDatatype, childrenOutputDatatype)) return false;
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
