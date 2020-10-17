package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.Clump;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.MgResolveTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public abstract class MgResolveExpressionTask extends MgResolveTask {
    @Input
    protected final CommandContext context;

    @Input
    private final MgResolveExpressionTask parentTask;

    @Mandatory @Part
    private final List<MgResolveExpressionTask> children = new List<>();

    public MgResolveExpressionTask(CommandContext context, MgResolveExpressionTask parentTask) {
        this.context = context;
        this.parentTask = parentTask;
    }

    public ReadableArray<MgInputConnector> getInputConnectors(){
        if(getExpression() == null) return null;
        return getExpression().getInputConnectors();
    }

    public ReadableArray<MgOutputConnector> getOutputConnectors(){
        if(getExpression() == null) return null;
        return getExpression().getOutputConnectors();
    }

    public MgExpression getParent() {
        if(parentTask == null) return null;
        return parentTask.getExpression();
    }

    public ReadableArray<MgInputConnector> getParentInputConnectors(){
        if(parentTask == null) return null;
        return parentTask.getInputConnectors();
    }

    protected static ReadableArray<MgOutputConnector> getChildrenOutputConnectors(MgExpression... expressions){
        return getChildrenOutputConnectors(new Array<>(expressions));
    }

    protected static ReadableArray<MgOutputConnector> getChildrenOutputConnectors(Clump<MgExpression> children){
        List<MgOutputConnector> outputConnectors = new List<>();
        for(MgExpression child : children){
            if(child.getOutputConnectors().count() == 0){
                throw new LanguageException("Empty expression output in a group is not allowed.");
            }
            for(MgOutputConnector outputConnector : child.getOutputConnectors()){
                outputConnectors.addLast(outputConnector);
            }
        }
        return new Array<>(outputConnectors);
    }

    @Override
    protected final void onRun() {
        context.getVariableHelper().sink();

        onResolve();
        validate();

        context.getVariableHelper().raise();
    }

    protected abstract void onResolve();

    private void validate(){
        if(getExpression() != null){
            getExpression().validate();
        } else {
            throw new LanguageException("Could not resolve expression.");
        }
    }

    public abstract MgExpression getExpression();

    public static MgResolveExpressionTask create(
        CommandContext context,
        MgLogicalCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ){
        if(logicalExpression instanceof MgLogicalNameCallExpression) {
            return new MgResolveNameExpressionTask(context, (MgLogicalNameCallExpression) logicalExpression, parent);
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

        if(logicalExpression instanceof MgLogicalMemberAccessCallExpression){
            return new MgResolveMemberAccessExpression(context, (MgLogicalMemberAccessCallExpression) logicalExpression, parent);
        }

        throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }
}
