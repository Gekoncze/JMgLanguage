package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveOperatorCallExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalOperatorCallExpression logicalExpression;

    @Output
    private MgOperatorExpression expression;

    public MgResolveOperatorCallExpressionTask(CommandContext context, MgLogicalOperatorCallExpression logicalExpression, Node parent) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public MgOperatorExpression getExpression() {
        return expression;
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
