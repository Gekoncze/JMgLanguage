package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalParametrizedExpression;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveParametrizedExpressionTask extends MgResolveExpressionTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalParametrizedExpression logicalExpression;

    @Output
    private Expression expression;

    public MgResolveParametrizedExpressionTask(CommandContext context, MgLogicalParametrizedExpression logicalExpression) {
        this.context = context;
        this.logicalExpression = logicalExpression;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    protected void onRun() {
        //todo;
    }
}
