package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalOperatorCallExpression;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveOperatorCallExpressionTask extends MgResolveExpressionTask<MgLogicalOperatorCallExpression> {
    public MgResolveOperatorCallExpressionTask(CommandContext context, MgLogicalOperatorCallExpression logicalExpression, Expression parent) {
        super(context, logicalExpression, parent);
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> onResolveEnter() {
        //todo;
        return null;
    }

    @Override
    protected void onResolveLeave() {
        //todo;
    }
}
