package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalOperatorExpression;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveOperatorExpressionTask extends MgResolveExpressionTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalOperatorExpression logicalExpression;

    @Output
    private Expression expression;

    public MgResolveOperatorExpressionTask(CommandContext context, MgLogicalOperatorExpression logicalExpression) {
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
