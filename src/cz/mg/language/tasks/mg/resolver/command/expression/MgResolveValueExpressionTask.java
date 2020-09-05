package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalValueCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgValueExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveValueExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalValueCallExpression logicalExpression;

    @Output
    private MgValueExpression expression;

    public MgResolveValueExpressionTask(CommandContext context, MgLogicalValueCallExpression logicalExpression, Node parent) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public MgValueExpression getExpression() {
        return expression;
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
