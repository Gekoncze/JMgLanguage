package cz.mg.language.tasks.mg.resolver.filter.expression.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.filter.expression.ExpressionFilter;


public abstract class OperatorExpressionFilter extends ExpressionFilter<MgOperator> {
    @Mandatory @Value
    protected final int replication;

    public OperatorExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Optional MgExpression destination,
        @Optional MgExpression leftSource,
        @Optional MgExpression rightSource,
        @Mandatory int replication
    ) {
        super(context, requiredName, MgOperator.class, destination, leftSource, rightSource);
        this.replication = replication;
    }

    protected @Optional MgOperator filterByDestination(@Optional MgOperator operator){
        if(operator == null){
            return null;
        }

        if(destinationInputInterface != null){
            if(operator.getOutputVariables().count() != 1) return null;
            MgDatatype destinationDatatype = destinationInputInterface.get(replication).getDatatype();
            MgDatatype sourceDatatype = operator.getOutputVariables().getFirst().getDatatype();
            if(!MgDatatype.isCompatible(destinationDatatype, sourceDatatype)) return null;
        }

        return operator;
    }
}
