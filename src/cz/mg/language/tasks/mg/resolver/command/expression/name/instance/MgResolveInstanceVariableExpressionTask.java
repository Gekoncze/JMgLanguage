package cz.mg.language.tasks.mg.resolver.command.expression.name.instance;

import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalChildCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalMemberNameCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveInstanceVariableExpressionTask extends MgResolveInstanceNameExpressionTask {
    public MgResolveInstanceVariableExpressionTask(
        CommandContext context,
        MgLogicalMemberNameCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, logicalExpression, parent);
    }
}
