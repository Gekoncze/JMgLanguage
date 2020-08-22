package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalFunctionCallExpression;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveFunctionCallExressionTask extends MgResolveExpressionTask<MgLogicalFunctionCallExpression> {
    public MgResolveFunctionCallExressionTask(CommandContext context, MgLogicalFunctionCallExpression logicalExpression, Expression parent) {
        super(context, logicalExpression, parent);
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> onResolveEnter() {
        todo;
    }

    @Override
    protected void onResolveLeave() {
        todo;
    }
}
