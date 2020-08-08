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
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public abstract class MgResolveExpressionTask extends MgResolverTask {
    @Input
    protected final CommandContext context;

    @Input
    protected final Expression parent;

    @Output
    protected Expression expression;

    @Subtask
    private final List<MgResolveExpressionTask> subtasks = new List<>();

    public MgResolveExpressionTask(CommandContext context, Expression parent) {
        this.context = context;
        this.parent = parent;
    }

    public final Expression getExpression() {
        return expression;
    }

    @Override
    protected final void onRun() {
        ReadableCollection<MgLogicalExpression> children = onResolveEnter();

        for(MgLogicalExpression child : children){
            expression.getExpressions().addLast(
                onResolveChild(child)
            );
        }

        onResolveLeave();
    }

    protected abstract ReadableCollection<MgLogicalExpression> onResolveEnter();

    protected Expression onResolveChild(MgLogicalExpression child){
        subtasks.addLast(create(context, child));
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
        if(logicalExpression instanceof MgLogicalValueExpression){
            return new MgResolveValueExpressionTask(context, (MgLogicalValueExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalNameExpression) {
            return new MgResolveNameExpressionTask(context, (MgLogicalNameExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalDeclarationExpression){
            return new MgResolveDeclarationExpressionTask(context, (MgLogicalDeclarationExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalOperatorExpression){
            if(optional) return null;
            throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " '" + ((MgLogicalOperatorExpression) logicalExpression).getTarget() + "' for resolve.");
        }

        if(logicalExpression instanceof MgLogicalSymbolExpression){
            if(optional) return null;
            throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " '" + ((MgLogicalSymbolExpression) logicalExpression).getSymbol() + "' for resolve.");
        }

        if(logicalExpression instanceof MgLogicalGroupExpression){
            return new MgResolveOperatorExpressionTask(context, (MgLogicalGroupExpression) logicalExpression, parent);
        }

        if(optional) return null;
        throw new LanguageException("Unsupported expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }
}
