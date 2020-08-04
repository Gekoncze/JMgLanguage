package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalNameExpression;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveNameExpressionTask extends MgResolveExpressionTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalNameExpression logicalExpression;

    @Output
    private Expression expression;

    public MgResolveNameExpressionTask(CommandContext context, MgLogicalNameExpression logicalExpression) {
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
