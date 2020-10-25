package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.LanguageException;
import cz.mg.language.Todo;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.MgResolveTask;
import cz.mg.language.tasks.mg.resolver.command.expression.name.MgResolveNameExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.name.instance.MgResolveInstanceNameExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.operator.MgResolveOperatorExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.other.MgResolveGroupExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.other.MgResolveValueExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public abstract class MgResolveExpressionTask extends MgResolveTask {
    @Input
    protected final CommandContext context;

    @Input
    private final MgResolveExpressionTask parentTask;

    public MgResolveExpressionTask(CommandContext context, MgResolveExpressionTask parentTask) {
        this.context = context;
        this.parentTask = parentTask;
        new Todo();
    }

//    public ReadableArray<MgInputConnector> getInputConnectors(){
//        if(getExpression() == null) return null;
//        return getExpression().getCache().getInputConnectors();
//    }
//
//    public ReadableArray<MgOutputConnector> getOutputConnectors(){
//        if(getExpression() == null) return null;
//        return getExpression().getCache().getOutputConnectors();
//    }

    public MgExpression getParent() {
        if(parentTask == null) return null;
        return parentTask.getExpression();
    }

//    public ReadableArray<MgInputConnector> getParentInputConnectors(){
//        if(parentTask == null) return null;
//        return parentTask.getInputConnectors();
//    }

//    protected static ReadableArray<MgOutputConnector> getChildrenOutputConnectors(MgExpression... expressions){
//        return getChildrenOutputConnectors(new Array<>(expressions));
//    }
//
//    protected static ReadableArray<MgOutputConnector> getChildrenOutputConnectors(Clump<MgExpression> children){
//        List<MgOutputConnector> outputConnectors = new List<>();
//        for(MgExpression child : children){
//            if(child.getOutputConnectors().count() == 0){
//                throw new LanguageException("Empty expression output in a group is not allowed.");
//            }
//            for(MgOutputConnector outputConnector : child.getOutputConnectors()){
//                outputConnectors.addLast(outputConnector);
//            }
//        }
//        return new Array<>(outputConnectors);
//    }

    @Override
    protected final void onRun() {
        context.getVariableHelper().sink();

        onResolve();

        context.getVariableHelper().raise();
    }

    protected abstract void onResolve();

    public abstract MgExpression getExpression();

    public static MgResolveExpressionTask create(
        CommandContext context,
        MgLogicalCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ){
        if(logicalExpression instanceof MgLogicalNameCallExpression) {
            return MgResolveNameExpressionTask.create(context, (MgLogicalNameCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalValueCallExpression){
            return new MgResolveValueExpressionTask(context, (MgLogicalValueCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalOperatorCallExpression){
            return MgResolveOperatorExpressionTask.create(context, (MgLogicalOperatorCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalGroupCallExpression){
            return new MgResolveGroupExpressionTask(context, (MgLogicalGroupCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalMemberNameCallExpression){
            return MgResolveInstanceNameExpressionTask.create(context, (MgLogicalMemberNameCallExpression) logicalExpression, parent);
        }

        throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }
}
