package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalValueCallExpression;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveValueExpressionTask extends MgResolveExpressionTask<MgLogicalValueCallExpression> {
    public MgResolveValueExpressionTask(CommandContext context, MgLogicalValueCallExpression logicalExpression, Expression parent) {
        super(context, logicalExpression, parent);
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> onResolveEnter() {
        // todo
        return null;
    }

    @Override
    protected void onResolveLeave() {
        // todo
    }
}
