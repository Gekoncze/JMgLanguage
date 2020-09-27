package cz.mg.language.tasks.mg.resolver.command.expression.special;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.BooleanNode;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveBooleanExpression extends MgResolveExpressionTask {
    @Input
    private final MgLogicalCallExpression logicalExpression;

    public MgResolveBooleanExpression(CommandContext context, MgLogicalCallExpression logicalExpression) {
        super(context, null);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected Node onResolveEnter() {
        return new BooleanNode();
    }

    @Override
    protected void onResolveChildren() {
        onResolveChild(logicalExpression);
    }

    @Override
    protected Node onResolveLeave() {
        return null;
    }

    @Override
    protected MgExpression onCreateExpression() {
        return getChildren().getLast().createExpression();
    }

    public MgLocalVariable getVariable() {
        return getNode().getInputInterface().getConnectors().getFirst().getConnection().getConnectionVariable();
    }
}
