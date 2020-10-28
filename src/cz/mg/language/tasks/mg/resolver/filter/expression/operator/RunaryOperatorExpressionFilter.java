package cz.mg.language.tasks.mg.resolver.filter.expression.operator;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgRunaryOperator;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class RunaryOperatorExpressionFilter extends UnaryOperatorExpressionFilter<MgRunaryOperator> {
    public RunaryOperatorExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Optional MgExpression destination,
        @Optional MgExpression leftSource,
        @Mandatory int replication
    ) {
        super(context, requiredName, MgRunaryOperator.class, destination, leftSource, null, replication);
    }

    @Override
    protected @Optional MgRunaryOperator filter(@Optional MgRunaryOperator operator) {
        return filterBySource(filterByDestination(operator));
    }

    private @Optional MgRunaryOperator filterBySource(@Optional MgRunaryOperator operator){
        if(operator == null){
            return null;
        }

        if(leftSourceOutputInterface != null){
            if(operator.getInputVariables().count() != 1) return null;
            MgDatatype sourceDatatype = leftSourceOutputInterface.get(replication).getDatatype();
            MgDatatype destinationDatatype = operator.getOutputVariables().getFirst().getDatatype();
            if(!MgDatatype.isCompatible(destinationDatatype, sourceDatatype)) return null;
        }

        return operator;
    }
}
