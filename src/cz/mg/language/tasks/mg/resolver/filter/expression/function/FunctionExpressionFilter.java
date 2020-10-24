package cz.mg.language.tasks.mg.resolver.filter.expression.function;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.filter.expression.ExpressionFilter;


public abstract class FunctionExpressionFilter extends ExpressionFilter<MgFunction> {
    public FunctionExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Optional MgExpression destination,
        @Optional MgExpression leftSource,
        @Optional MgExpression rightSource
    ) {
        super(context, requiredName, MgFunction.class, destination, leftSource, rightSource);
    }

    protected @Optional MgFunction filterByDestination(MgFunction function){
        if(function == null){
            return null;
        }

        if(destinationInputInterface != null){
            if(destinationInputInterface.count() < function.getOutput().count()) return null;
            for(int i = 0; i < function.getOutput().count(); i++){
                MgDatatype destinationDatatype = destinationInputInterface.get(i).getDatatype();
                MgDatatype sourceDatatype = function.getOutput().get(i).getDatatype();
                if(!MgDatatype.isCompatible(destinationDatatype, sourceDatatype)) return null;
            }
        }

        return function;
    }
}
