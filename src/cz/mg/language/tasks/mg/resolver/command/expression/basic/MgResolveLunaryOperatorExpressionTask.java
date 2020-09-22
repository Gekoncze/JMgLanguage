package cz.mg.language.tasks.mg.resolver.command.expression.basic;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalLunaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.basic.MgLunaryOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.LunaryOperatorNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveLunaryOperatorExpressionTask extends MgResolveUnaryOperatorExpressionTask {
    @Input
    private final MgLogicalLunaryOperatorCallExpression logicalExpression;

    public MgResolveLunaryOperatorExpressionTask(
        CommandContext context,
        MgLogicalLunaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, logicalExpression, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected void onResolveChildren() {
        onResolveChild(logicalExpression.getRight());
    }

    @Override
    protected Node onResolveLeave() {
        return new LunaryOperatorNode(resolveFunctions());
    }

    @Override
    public MgExpression createExpression() {
        return new MgLunaryOperatorExpression(
            createChildExpression(),
            createReplications()
        );
    }
}
