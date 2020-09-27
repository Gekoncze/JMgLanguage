package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.MgVariable;
import cz.mg.language.entities.mg.runtime.components.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.roles.MgObject;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class NameExpressionFilter extends AbstractClassFilter<MgObject> {
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
        this(
            context,
            name,
            parentInputInterface,
            childrenOutputInterface,
            null
        );
    }

    public NameExpressionFilter(
        @Mandatory Context context,
        @Optional ReadableText name,
        @Optional InputInterface parentInputInterface,
        @Optional OutputInterface childrenOutputInterface,
        @Optional MgDatatype self
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
            childrenOutputInterface,
            self
        );
    }

    @Override
    protected boolean filter(MgObject object, ReadableText alias) {
        if(super.filter(object, alias)){
            if(variableExpressionFilter.filter(object, alias)){
                return true;
            }

            if(functionExpressionFilter.filter(object, alias)){
                return true;
            }
        }
        return false;
    }
}
