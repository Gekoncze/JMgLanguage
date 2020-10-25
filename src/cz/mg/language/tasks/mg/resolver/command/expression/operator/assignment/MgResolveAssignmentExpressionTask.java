package cz.mg.language.tasks.mg.resolver.command.expression.operator.assignment;

import cz.mg.language.Todo;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Utility;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.operator.MgResolveOperatorExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.special.MgResolveVoidExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public abstract class MgResolveAssignmentExpressionTask extends MgResolveOperatorExpressionTask {
    @Input
    protected final MgLogicalCallExpression sourceLogicalExpression;

    @Input
    protected final MgLogicalCallExpression destinationLogicalExpression;

    @Utility
    protected MgResolveVoidExpressionTask voidTask;

    public MgResolveAssignmentExpressionTask(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.sourceLogicalExpression = logicalExpression.getRight();
        this.destinationLogicalExpression = logicalExpression.getLeft();
        new Todo();
    }

//    @Override
//    protected final Node onResolveEnter() {
//        voidTask = new MgResolveVoidExpressionTask(context, destinationLogicalExpression);
//        voidTask.run();
//        return new AssignmentNode(voidTask.getInputConnectors());
//    }
//
//    @Override
//    protected void onResolveChildren() {
//        onResolveChild(sourceLogicalExpression);
//    }
//
//    @Override
//    protected final Node onResolveLeave() {
//        // resolve enter should always create node
//        return null;
//    }
}
