package cz.mg.language.tasks.mg.resolver.filter.expression.function;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class StandaloneFunctionExpressionFilter extends FunctionExpressionFilter {
    public StandaloneFunctionExpressionFilter(
        @Optional CommandContext context,
        @Mandatory ReadableText requiredName,
        @Optional MgExpression destination,
        @Optional MgExpression parameters
    ) {
        super(context, requiredName, destination, null, parameters);
    }

    @Override
    protected @Optional MgFunction filter(@Optional MgFunction function) {
        return filterByParameters(filterByDestination(function));
    }

    private @Optional MgFunction filterByParameters(@Optional MgFunction function){
        if(function == null){
            return null;
        }

        if(rightSourceOutputInterface != null){
            if(function.getInputVariables().count() != rightSourceOutputInterface.count()) return null;
            for(int i = 0; i < function.getInputVariables().count(); i++){
                MgDatatype destinationDatatype = function.getInputVariables().get(i).getDatatype();
                MgDatatype sourceDatatype = rightSourceOutputInterface.get(i).getDatatype();
                if(!MgDatatype.isCompatible(destinationDatatype, sourceDatatype)) return null;
            }
        }

        return function;
    }
}
