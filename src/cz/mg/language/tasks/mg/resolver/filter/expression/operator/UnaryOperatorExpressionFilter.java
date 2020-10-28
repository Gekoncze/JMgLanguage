package cz.mg.language.tasks.mg.resolver.filter.expression.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgUnaryOperator;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.Context;


public abstract class UnaryOperatorExpressionFilter<O extends MgUnaryOperator> extends OperatorExpressionFilter<O> {
    public UnaryOperatorExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Mandatory Class<O> clazz,
        @Optional MgExpression destination,
        @Optional MgExpression leftSource,
        @Optional MgExpression rightSource,
        @Mandatory int replication
    ) {
        super(context, requiredName, clazz, destination, leftSource, rightSource, replication);
    }
}
