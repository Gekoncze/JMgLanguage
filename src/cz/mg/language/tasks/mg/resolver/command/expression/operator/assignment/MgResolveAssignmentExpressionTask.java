package cz.mg.language.tasks.mg.resolver.command.expression.operator.assignment;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.operator.MgResolveOperatorExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public abstract class MgResolveAssignmentExpressionTask extends MgResolveOperatorExpressionTask {
    @Input
    protected final MgLogicalBinaryOperatorCallExpression logicalExpression;

    public MgResolveAssignmentExpressionTask(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        ExpectedParentInput parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }
}
