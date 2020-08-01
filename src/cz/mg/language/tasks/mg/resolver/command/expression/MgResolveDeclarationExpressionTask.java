package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalDeclarationExpression;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveDeclarationExpressionTask extends MgResolveExpressionTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalDeclarationExpression logicalExpression;

    @Output
    private Expression expression;

    public MgResolveDeclarationExpressionTask(CommandContext context, MgLogicalDeclarationExpression logicalExpression) {
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
