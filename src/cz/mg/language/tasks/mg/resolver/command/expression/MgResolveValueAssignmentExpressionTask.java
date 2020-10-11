package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveValueAssignmentExpressionTask extends MgResolveAssignmentExpressionTask {
    public MgResolveValueAssignmentExpressionTask(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, logicalExpression, parent);
    }

    todo;
}
