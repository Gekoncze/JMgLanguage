package cz.mg.language.tasks.mg.resolver.command.expression.basic;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalValueCallExpression;
import cz.mg.language.entities.mg.runtime.atoms.MgTextObject;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.basic.MgValueExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.ValueNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveValueExpressionTask extends MgResolveBasicExpressionTask {
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
        return new ValueNode(new MgTextObject(logicalExpression.getValue().toString()));
    }

    @Override
    public MgExpression createExpression() {
        return new MgValueExpression(
            getNode().getValue(),
            getOutputInterface().getConnectors().getFirst().getConnection().getConnectionVariable()
        );
    }
}
