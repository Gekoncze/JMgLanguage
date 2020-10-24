package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalLunaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.operator.MgLunaryOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


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
    public MgExpression onCreateExpression() {
        return new MgLunaryOperatorExpression(
            createChildExpression(),
            createReplications()
        );
    }
}
