package cz.mg.language.tasks.mg.resolver.command.expression.special;

import cz.mg.language.Todo;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveExceptionExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalCallExpression logicalExpression;

    public MgResolveExceptionExpressionTask(CommandContext context, MgLogicalCallExpression logicalExpression) {
        super(context, null);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected void onResolve() {
        new Todo();
    }

    @Override
    public MgExpression getExpression() {
        new Todo();
        return null;
    }

//    @Override
//    protected Node onResolveEnter() {
//        return new ExceptionNode();
//    }
//
//    @Override
//    protected void onResolveChildren() {
//        onResolveChild(logicalExpression);
//    }
//
//    @Override
//    protected Node onResolveLeave() {
//        return null;
//    }
//
//    @Override
//    protected MgExpression onCreateExpression() {
//        return getChildren().getLast().createExpression();
//    }
//
//    public MgFunctionVariable getVariable() {
//        return getNode().getInputInterface().getConnectors().getFirst().getConnection().getConnectionVariable();
//    }
}
