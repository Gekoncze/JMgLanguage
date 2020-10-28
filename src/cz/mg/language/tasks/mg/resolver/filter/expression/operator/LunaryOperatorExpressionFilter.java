package cz.mg.language.tasks.mg.resolver.filter.expression.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgLunaryOperator;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class LunaryOperatorExpressionFilter extends UnaryOperatorExpressionFilter<MgLunaryOperator> {
    public LunaryOperatorExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Optional MgExpression destination,
        @Optional MgExpression rightSource,
        @Mandatory int replication
    ) {
        super(context, requiredName, MgLunaryOperator.class, destination, null, rightSource, replication);
    }

    @Override
    protected @Optional MgLunaryOperator filter(@Optional MgLunaryOperator operator) {
        return filterBySource(filterByDestination(operator));
    }

    private @Optional MgLunaryOperator filterBySource(@Optional MgLunaryOperator operator){
        if(operator == null){
            return null;
        }

        if(rightSourceOutputInterface != null){
            if(operator.getInputVariables().count() != 1) return null;
            MgDatatype sourceDatatype = rightSourceOutputInterface.get(replication).getDatatype();
            MgDatatype destinationDatatype = operator.getOutputVariables().getFirst().getDatatype();
            if(!MgDatatype.isCompatible(destinationDatatype, sourceDatatype)) return null;
        }

        return operator;
    }
}
