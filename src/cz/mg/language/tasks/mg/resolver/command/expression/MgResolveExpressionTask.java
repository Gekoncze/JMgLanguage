package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalFunctionCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public abstract class MgResolveExpressionTask<LogicalExpression extends MgLogicalExpression> extends MgResolverTask {
    @Input
    protected final CommandContext context;

    @Input
    protected final LogicalExpression logicalExpression;

    @Input
    protected final Expression parent;

    @Output
    protected Expression expression;

    @Subtask
    private final List<MgResolveExpressionTask> subtasks = new List<>();

    public MgResolveExpressionTask(CommandContext context, LogicalExpression logicalExpression, Expression parent) {
        this.context = context;
        this.logicalExpression = logicalExpression;
        this.parent = parent;
    }

    public final Expression getExpression() {
        return expression;
    }

    @Override
    protected final void onRun() {
        expression = new Expression(logicalExpression);
        expression.setOutput(parent.getInput());

        ReadableCollection<MgLogicalExpression> children = onResolveEnter();
        expression.setInput(todo);

        for(MgLogicalExpression child : children){
            expression.getExpressions().addLast(
                onResolveChild(child)
            );
        }
        expression.setInput(todo);

        onResolveLeave();
        expression.setOutput(todo);

        if(expression.getInput() == null) throw new RuntimeException();
        if(expression.getOutput() == null) throw new RuntimeException();
    }

    protected abstract ReadableCollection<MgLogicalExpression> onResolveEnter();

    protected Expression onResolveChild(MgLogicalExpression child){
        subtasks.addLast(create(context, child, expression));
        subtasks.getLast().run();
        return subtasks.getLast().getExpression();
    }

    protected abstract void onResolveLeave();

    protected ReadableArray<MgVariable> io(){
        return new Array<>();
    }

    protected ReadableArray<MgVariable> io(MgVariable... io){
        return new Array<>(io);
    }

    protected ReadableArray<MgVariable> io(ReadableArray<MgVariable> io){
        return io;
    }

    protected ReadableArray<MgVariable> io(ReadableList<MgVariable> io){
        return new Array(io);
    }

    public static MgResolveExpressionTask create(CommandContext context, MgLogicalExpression logicalExpression, Expression parent){
        if(logicalExpression instanceof MgLogicalGroupExpression){
            logicalExpression =
        }

        if(logicalExpression instanceof MgLogicalNameExpression) {
            todo;
        }

        else if(logicalExpression instanceof MgLogicalOperatorExpression){
            todo;
        }

        else if(logicalExpression instanceof MgLogicalValueExpression){
            todo;
        }

        else if(logicalExpression instanceof MgLogicalFunctionCallExpression){
            todo;
        }

        else if(logicalExpression instanceof MgLogicalOperatorCallExpression){
            todo;
        }

        else {
            throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
        }
    }
}
