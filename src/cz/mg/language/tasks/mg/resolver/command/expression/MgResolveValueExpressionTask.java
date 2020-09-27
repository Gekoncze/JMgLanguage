package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalValueCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgValueExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.ValueNode;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveValueExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalValueCallExpression logicalExpression;

    public MgResolveValueExpressionTask(
        CommandContext context,
        MgLogicalValueCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public ValueNode getNode() {
        return (ValueNode) super.getNode();
    }

    @Override
    protected Node onResolveEnter() {
        return null;
    }

    @Override
    protected void onResolveChildren() {
    }

    @Override
    protected Node onResolveLeave() {
        return new ValueNode(getParentInputInterface(), logicalExpression.getValue());
    }

    @Override
    public MgExpression onCreateExpression() {
        return new MgValueExpression(
            getNode().getValue(),
            getOutputInterface().getConnectors().getFirst().getConnection().getConnectionVariable()
        );
    }
}
