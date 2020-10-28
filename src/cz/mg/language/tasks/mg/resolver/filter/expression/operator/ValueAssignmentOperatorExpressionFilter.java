package cz.mg.language.tasks.mg.resolver.filter.expression.operator;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgBinaryOperator;
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
    protected @Optional MgBinaryOperator filterByDestination(@Optional MgBinaryOperator operator) {
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
