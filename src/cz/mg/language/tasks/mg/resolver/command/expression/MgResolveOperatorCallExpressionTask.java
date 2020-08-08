package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalOperatorCallExpression;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveOperatorCallExpressionTask extends MgResolveExpressionTask<MgLogicalOperatorCallExpression> {
    public MgResolveOperatorCallExpressionTask(CommandContext context, MgLogicalOperatorCallExpression logicalExpression, Expression parent) {
        super(context, logicalExpression, parent);
    }

    @Override
    protected ReadableCollection<MgLogicalExpression> onResolveEnter() {
        todo;
    }

    @Override
    protected void onResolveLeave() {
        todo;
    }
}
