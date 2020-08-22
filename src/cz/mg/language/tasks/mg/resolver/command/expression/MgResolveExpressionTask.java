package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.*;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public abstract class MgResolveExpressionTask<LogicalExpression extends MgLogicalCallExpression> extends MgResolverTask {
    @Input
    protected final CommandContext context;

    @Input
    protected final LogicalExpression logicalExpression;

    @Input
    protected final Expression parent;

    @Output
    protected final Expression expression;

    @Subtask
    private final List<MgResolveExpressionTask> subtasks = new List<>();

    public MgResolveExpressionTask(CommandContext context, LogicalExpression logicalExpression, Expression parent) {
        this.context = context;
        this.logicalExpression = logicalExpression;
        this.parent = parent;
        this.expression = new Expression(logicalExpression);
    }

    public final Expression getExpression() {
        return expression;
    }

    @Override
    protected final void onRun() {
        ReadableCollection<MgLogicalCallExpression> logicalChildren = onResolveEnter();

        for(MgLogicalCallExpression logicalChild : logicalChildren){
            Expression child = onResolveChild(logicalChild);
            expression.getExpressions().addLast(child);
            if(expression.getInputInterface() != null) Expression.connect(expression, child);
        }

        onResolveLeave();
        for(Expression child : expression.getExpressions()) Expression.connect(expression, child);
    }

    protected abstract ReadableCollection<MgLogicalCallExpression> onResolveEnter();

    protected Expression onResolveChild(MgLogicalCallExpression child){
        subtasks.addLast(create(context, child, expression));
        subtasks.getLast().run();
        return subtasks.getLast().getExpression();
    }

    protected abstract void onResolveLeave();

    public static MgResolveExpressionTask create(CommandContext context, MgLogicalCallExpression logicalExpression, Expression parent){
        if(logicalExpression instanceof MgLogicalVariableCallExpression) {
            return new MgResolveNameExpressionTask(context, (MgLogicalVariableCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalValueCallExpression){
            return new MgResolveValueExpressionTask(context, (MgLogicalValueCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalFunctionCallExpression){
            return new MgResolveFunctionCallExressionTask(context, (MgLogicalFunctionCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalOperatorCallExpression){
            return new MgResolveOperatorCallExpressionTask(context, (MgLogicalOperatorCallExpression) logicalExpression, parent);
        }

        throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }
}
