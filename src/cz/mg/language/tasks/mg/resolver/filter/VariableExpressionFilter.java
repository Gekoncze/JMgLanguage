package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class VariableExpressionFilter extends ClassFilter<MgVariable> {
    @Optional @Link
    private final InputInterface parentInputInterface;

    @Optional @Link
    private final List<OutputInterface> childrenOutputInterface;

    public VariableExpressionFilter(
        @Mandatory Context context,
        @Optional ReadableText name,
        @Optional InputInterface parentInputInterface,
        @Optional List<OutputInterface> childrenOutputInterface
    ) {
        super(context, name, MgVariable.class);
        this.parentInputInterface = parentInputInterface;
        this.childrenOutputInterface = childrenOutputInterface;
    }

    @Override
    protected boolean filter(MgComponent component) {
        if(super.filter(component)){
            if(component instanceof MgVariable){
                return filterVariable((MgVariable) component);
            }
        }
        return false;
    }

    private boolean filterVariable(MgVariable variable){
        // variables can have only one output value
        if(parentInputInterface != null){
            if(parentInputInterface.getRemainingConnectors().count() < 1) return false;
            if(!Matcher.matches(
                parentInputInterface.getRemainingConnectors().getFirst().getRequestedDatatype(),
                variable.getDatatype()
            )) return false;
        }

        // variables cannot have any input value
        if(childrenOutputInterface != null){
            if(childrenOutputInterface.count() > 0) return false;
        }

        return true;
    }
}
