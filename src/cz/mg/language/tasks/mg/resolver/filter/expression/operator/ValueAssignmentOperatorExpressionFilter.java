package cz.mg.language.tasks.mg.resolver.filter.expression.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class ValueAssignmentOperatorExpressionFilter extends BinaryOperatorExpressionFilter {
    public ValueAssignmentOperatorExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Optional MgExpression destination,
        @Optional MgExpression leftSource,
        @Optional MgExpression rightSource,
        @Mandatory int replication
    ) {
        super(context, requiredName, destination, leftSource, rightSource, replication);
    }

    @Override
    protected @Optional MgOperator filterByDestination(@Optional MgOperator operator) {
        if(operator == null){
            return null;
        }

        if(destinationInputInterface != null){
            if(destinationInputInterface.count() > 0){
                return null;
            }
        }

        return operator;
    }
}
