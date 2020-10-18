package cz.mg.language.tasks.mg.resolver.command.expression.name;

import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveVariableExpressionTask extends MgResolveNameExpressionTask {
    public MgResolveVariableExpressionTask(
        CommandContext context,
        MgLogicalNameCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, logicalExpression, parent);
    }
}
