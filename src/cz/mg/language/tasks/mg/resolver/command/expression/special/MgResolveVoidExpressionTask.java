package cz.mg.language.tasks.mg.resolver.command.expression.special;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.special.VoidNode;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveVoidExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalCallExpression logicalExpression;

    public MgResolveVoidExpressionTask(CommandContext context, MgLogicalCallExpression logicalExpression) {
        super(context, null);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected Node onResolveEnter() {
        return null;
    }

    @Override
    protected void onResolveChildren() {
        onResolveChild(logicalExpression);
    }

    @Override
    protected Node onResolveLeave() {
        return new VoidNode(getChildren().getFirst().getOutputInterface());
    }

    @Override
    protected MgExpression onCreateExpression() {
        return getChildren().getLast().createExpression();
    }

    public MgFunctionVariable getVariable() {
        return getNode().getInputInterface().getConnectors().getFirst().getConnection().getConnectionVariable();
    }
}
