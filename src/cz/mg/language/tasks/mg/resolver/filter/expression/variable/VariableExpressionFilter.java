package cz.mg.language.tasks.mg.resolver.filter.expression.variable;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.filter.expression.ExpressionFilter;


public abstract class VariableExpressionFilter extends ExpressionFilter<MgVariable> {
    public VariableExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Optional MgExpression destination,
        @Optional MgExpression leftSource,
        @Optional MgExpression rightSource
    ) {
        super(context, requiredName, MgVariable.class, destination, leftSource, rightSource);
    }

    protected @Optional MgVariable filterByDestination(@Optional MgVariable variable){
        if(variable == null){
            return null;
        }

        if(destinationInputInterface != null){
            if(destinationInputInterface.count() < 1) return null;
            MgDatatype destinationDatatype = destinationInputInterface.getFirst().getDatatype();
            MgDatatype sourceDatatype = variable.getDatatype();
            if(!MgDatatype.isCompatible(destinationDatatype, sourceDatatype)) return null;
        }

        return variable;
    }

    protected @Optional MgVariable filterByValue(@Optional MgVariable variable){
        if(variable == null){
            return null;
        }

        if(rightSourceOutputInterface != null){
            if(rightSourceOutputInterface.count() != 1) return null;
            MgDatatype destinationDatatype = variable.getDatatype();
            MgDatatype sourceDatatype = rightSourceOutputInterface.getFirst().getDatatype();
            if(!MgDatatype.isCompatible(destinationDatatype, sourceDatatype)) return null;
        }

        return variable;
    }
}
