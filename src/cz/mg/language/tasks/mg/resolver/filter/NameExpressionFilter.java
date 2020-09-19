package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class NameExpressionFilter extends ClassFilter<MgComponent> {
    @Mandatory @Part
    private final VariableExpressionFilter variableExpressionFilter;

    @Mandatory @Part
    private final FunctionExpressionFilter functionExpressionFilter;

    public NameExpressionFilter(
        @Mandatory Context context,
        @Optional ReadableText name,
        @Optional InputInterface parentInputInterface,
        @Optional OutputInterface childrenOutputInterface
    ) {
        super(context, name, MgVariable.class, MgFunction.class);
        this.variableExpressionFilter = new VariableExpressionFilter(
            context,
            name,
            parentInputInterface,
            childrenOutputInterface
        );
        this.functionExpressionFilter = new FunctionExpressionFilter(
            context,
            name,
            parentInputInterface,
            childrenOutputInterface
        );
    }

    @Override
    protected boolean filter(MgComponent component) {
        if(super.filter(component)){
            if(variableExpressionFilter.filter(component)){
                return true;
            }

            if(functionExpressionFilter.filter(component)){
                return true;
            }
        }
        return false;
    }
}
