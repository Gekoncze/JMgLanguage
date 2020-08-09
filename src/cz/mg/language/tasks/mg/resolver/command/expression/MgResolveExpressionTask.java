package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ArrayView;
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
        expression.setOutput(getRemainingInput(parent));

        ReadableCollection<MgLogicalExpression> logicalChildren = onResolveEnter();

        List<MgVariable> actualInput = new List<>();
        for(MgLogicalExpression logicalChild : logicalChildren){
            Expression child = onResolveChild(logicalChild);
            expression.getExpressions().addLast(child);
            actualInput.addCollectionLast(child.getOutput());
        }
        if(expression.getInput() == null) expression.setInput(actualInput);

        onResolveLeave();
        expression.setOutput(todo);

        if(!Matcher.matches(expression.getInput(), actualInput)) throw new RuntimeException(); // optional
        if(!Matcher.matchesPartial(parent.getRemainingInput(), expression.getOutput())) throw new RuntimeException(); // optional
    }

    protected abstract ReadableCollection<MgLogicalExpression> onResolveEnter();

    protected Expression onResolveChild(MgLogicalExpression child){
        subtasks.addLast(create(context, child, expression));
        subtasks.getLast().run();
        return subtasks.getLast().getExpression();
    }

    protected abstract void onResolveLeave();

    protected ReadableArray<MgVariable> getRemainingInput(Expression parent){
        if(parent.getInput() == null) return null;
        int offset = 0;
        for(Expression expression : parent.getExpressions()){
            offset += expression.getOutput().count();
        }
        return new ArrayView<>(input, offset, input.count());
    }

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
            return new MgResolveGroupExpressionTask(context, (MgLogicalGroupExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalNameExpression) {
            return new MgResolveNameExpressionTask(context, (MgLogicalNameExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalOperatorExpression){
            throw new LanguageException("Orphan operator.");
        }

        if(logicalExpression instanceof MgLogicalValueExpression){
            return new MgResolveValueExpressionTask(context, (MgLogicalValueExpression) logicalExpression, parent);
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
