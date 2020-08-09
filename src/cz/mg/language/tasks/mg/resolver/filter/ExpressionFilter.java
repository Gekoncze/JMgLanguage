package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class ExpressionFilter extends ClassFilter<MgComponent> {
    @Optional @Link
    private final InputInterface parentInputInterface;

    @Optional @Link
    private final InputInterface expressionInputInterface;

    public ExpressionFilter(Context context, ReadableText name, InputInterface parentInputInterface, OutputInterface outputInterface) {
        super(context, name, MgVariable.class, MgFunction.class);
        this.parentInputInterface = parentInputInterface;
        this.childOutputInterface = outputInterface;
    }

    @Override
    protected boolean filter(MgComponent component) {
        if(super.filter(component)){
            if(component instanceof MgVariable){
                return filterVariable((MgVariable) component);
            }

            if(component instanceof MgFunction){
                return filterFunction((MgFunction) component);
            }
        }
        return false;
    }

    private boolean filterVariable(MgVariable variable){
        if(parentInputInterface != null){
            if(parentInputInterface.getConnectors().count() > 0) return false;
        }

        if(childOutputInterface != null){
            ReadableArray<OutputConnector> remainingConnectors = childOutputInterface.getRemainingConnectors();
            if(remainingConnectors.count() < 1) return false;
            if(!Matcher.matches(remainingConnectors.getFirst().getRequestedDatatype(), variable.getDatatype())) return false;
        }

        return true;
    }

    private boolean filterFunction(MgFunction function){
        if(parentInputInterface != null){
            ReadableArray<InputConnector> remainingConnectors = parentInputInterface.getRemainingConnectors();
            if(function.getInput() != null){
                if(!Matcher.matches(function.getInput(), parentInputInterface)) return false;
            } else {
                if(remainingConnectors.count() > 0){
                    return false;
                }
            }
        }

        if(childOutputInterface != null){
            ReadableArray<OutputConnector> remainingConnectors = childOutputInterface.getRemainingConnectors();
            if(function.getOutput() != null){
                if(remainingConnectors.count() < function.getOutput().count()) return false;
                for(int i = 0; i < function.getOutput().count(); i++){
                    if(!Matcher.matches())
                }
            } else {
                if(remainingConnectors.count() > 0){
                    return false;
                }
            }
        }

        return true;
    }
}
