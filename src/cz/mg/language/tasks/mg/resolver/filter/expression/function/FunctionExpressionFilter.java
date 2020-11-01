package cz.mg.language.tasks.mg.resolver.filter.expression.function;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.filter.expression.ExpressionFilter;


public abstract class FunctionExpressionFilter extends ExpressionFilter<MgFunction> {
    public FunctionExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Optional ExpectedParentInput destination,
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
            if(destinationInputInterface.count() < function.getOutputVariables().count()) return null;
            for(int i = 0; i < function.getOutputVariables().count(); i++){
                MgDatatype destinationDatatype = destinationInputInterface.get(i);
                if(destinationDatatype != null){
                    MgDatatype sourceDatatype = function.getOutputVariables().get(i).getDatatype();
                    if(!MgDatatype.isCompatible(destinationDatatype, sourceDatatype)) return null;
                }
            }
        }

        return function;
    }
}
