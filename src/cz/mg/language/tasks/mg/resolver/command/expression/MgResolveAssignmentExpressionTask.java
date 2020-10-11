package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveAssignmentExpressionTask extends MgResolveOperatorExpressionTask {
    @Input
    private final MgLogicalCallExpression sourceLogicalExpression;

    @Input
    private final MgLogicalCallExpression destinationLogicalExpression;

    public MgResolveAssignmentExpressionTask(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.sourceLogicalExpression = logicalExpression.getRight();
        this.destinationLogicalExpression = logicalExpression.getLeft();
    }

    @Override
    protected @Optional Node onResolveEnter() {
        // assignment operator cannot have parent expression, it has no return value
        return null;
    }

    @Override
    protected void onResolveChildren() {
        onResolveChild(sourceLogicalExpression);
    }

    @Override
    protected @Mandatory Node onResolveLeave() {
        todo;
    }

    @Override
    protected MgExpression onCreateExpression() {
        todo;
    }
}
