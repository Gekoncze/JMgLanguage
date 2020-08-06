package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalParametrizedExpression;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveParametrizedExpressionTask extends MgResolveExpressionTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalParametrizedExpression logicalExpression;

    @Input
    private final Expression parent;

    @Output
    private Expression expression;

    @Subtask
    private final List<MgResolveExpressionTask> subtasks = new List<>();

    public MgResolveParametrizedExpressionTask(CommandContext context, MgLogicalParametrizedExpression logicalExpression, Expression parent) {
        this.context = context;
        this.logicalExpression = logicalExpression;
        this.parent = parent;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    protected void onRun() {
        expression = new Expression(logicalExpression);

        todo; // todo - try to resolve function - first pass

        for(MgLogicalExpression argument : logicalExpression.getArguments()){
            subtasks.addLast(MgResolveExpressionTask.create(context, argument, expression));
            subtasks.getLast().run();
            expression.getExpressions().addLast(subtasks.getLast().getExpression());
        }

        todo; // todo - try to resolve function - second pass

        expression.setInput(todo);
        expression.setOutput(todo);
        expression.getInstructions().add(todo);
    }
}
