package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalRunaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgRunaryOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.RunaryOperatorNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveRunaryOperatorExpressionTask extends MgResolveUnaryOperatorExpressionTask {
    @Input
    private final MgLogicalRunaryOperatorCallExpression logicalExpression;

    public MgResolveRunaryOperatorExpressionTask(
        CommandContext context,
        MgLogicalRunaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, logicalExpression, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public RunaryOperatorNode getNode() {
        return (RunaryOperatorNode) super.getNode();
    }

    @Override
    protected void onResolveChildren() {
        onResolveChild(logicalExpression.getLeft());
    }

    @Override
    protected Node onResolveLeave() {
        return new RunaryOperatorNode(resolveFunctions());
    }

    @Override
    public MgExpression onCreateExpression() {
        return new MgRunaryOperatorExpression(
            createChildExpression(),
            createReplications()
        );
    }
}
