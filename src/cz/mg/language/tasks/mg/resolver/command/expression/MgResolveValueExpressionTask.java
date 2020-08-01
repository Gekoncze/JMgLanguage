package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalValueExpression;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveValueExpressionTask extends MgResolveExpressionTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalValueExpression logicalExpression;

    @Output
    private Expression expression;

    public MgResolveValueExpressionTask(CommandContext context, MgLogicalValueExpression logicalExpression) {
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
