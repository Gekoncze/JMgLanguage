package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalPathExpression;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolvePathExpressionTask extends MgResolveExpressionTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalPathExpression logicalExpression;

    @Output
    private Expression expression;

    public MgResolvePathExpressionTask(CommandContext context, MgLogicalPathExpression logicalExpression) {
        this.context = context;
        this.logicalExpression = logicalExpression;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    protected void onRun() {
        todo;
    }
}
