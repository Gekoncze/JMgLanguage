package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.roles.MgComponent;
import cz.mg.language.entities.mg.runtime.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.roles.MgObject;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class VariableExpressionFilter extends AbstractClassFilter<MgVariable> {
    @Optional @Link
    private final ReadableArray<InputConnector> parentInputInterface;

    @Optional @Link
    private final ReadableArray<OutputConnector> childrenOutputInterface;

    public VariableExpressionFilter(
        @Mandatory Context context,
        @Optional ReadableText name,
        @Optional InputInterface parentInputInterface,
        @Optional OutputInterface childrenOutputInterface
    ) {
        super(context, name, MgVariable.class);
        this.parentInputInterface = parentInputInterface != null ? parentInputInterface.getRemainingConnectors() : null;
        this.childrenOutputInterface = childrenOutputInterface != null ? childrenOutputInterface.getConnectors() : null;
    }

    @Override
    protected boolean filter(MgObject object, ReadableText alias) {
        if(super.filter(object, alias)){
            if(object instanceof MgVariable){
                return filterVariable((MgVariable) object);
            }
        }
        return false;
    }

    private boolean filterVariable(MgVariable variable){
        // variables can have only one output value
        if(parentInputInterface != null){
            if(parentInputInterface.count() < 1) return false;
            MgDatatype inputDatatype = parentInputInterface.getFirst().getRequestedDatatype();
            MgDatatype outputDatatype = variable.getDatatype();
            if(!Matcher.matches(inputDatatype, outputDatatype)) return false;
        }

        // variables cannot have any input value
        if(childrenOutputInterface != null){
            if(childrenOutputInterface.count() > 0) return false;
        }

        return true;
    }
}
