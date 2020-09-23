package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.annotations.task.Utility;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public abstract class MgResolveExpressionTask extends MgResolverTask {
    @Input
    protected final CommandContext context;

    @Input
    private final MgResolveExpressionTask parent;

    @Utility
    private Node node;

    @Subtask
    private final List<MgResolveExpressionTask> children = new List<>();

    public MgResolveExpressionTask(CommandContext context, MgResolveExpressionTask parent) {
        this.context = context;
        this.parent = parent;
    }

    public Node getNode() {
        return node;
    }

    public InputInterface getInputInterface(){
        if(node == null) return null;
        return node.getInputInterface();
    }

    public OutputInterface getOutputInterface(){
        if(node == null) return null;
        return node.getOutputInterface();
    }

    public MgResolveExpressionTask getParent() {
        return parent;
    }

    public InputInterface getParentInputInterface(){
        if(parent == null) return null;
        return parent.getInputInterface();
    }

    public List<MgResolveExpressionTask> getChildren() {
        return children;
    }

    public OutputInterface getChildrenOutputInterface(){
        List<OutputConnector> outputInterfaceConnectors = new List<>();
        for(MgResolveExpressionTask child : children){
            if(child.getOutputInterface().getConnectors().count() == 0){
                throw new LanguageException("Empty expression output in a group is not allowed.");
            }
            for(OutputConnector outputConnector : child.getOutputInterface().getConnectors()){
                outputInterfaceConnectors.addLast(outputConnector);
            }
        }
        return new OutputInterface(new Array<>(outputInterfaceConnectors));
    }

    @Override
    protected final void onRun() {
        context.getVariableHelper().sink();

        onResolve();
        verify();

        context.getVariableHelper().raise();
    }

    protected void onResolve(){
        if(node == null) setNode(onResolveEnter());
        onResolveChildren();
        if(node == null) setNode(onResolveLeave());
    }

    private void verify(){
        if(node == null){
            throw new LanguageException("Could not resolve expression.");
        }
    }

    protected abstract @Optional Node onResolveEnter();

    protected abstract void onResolveChildren();

    protected Node onResolveChild(MgLogicalCallExpression childLogicalExpression){
        MgResolveExpressionTask child = create(context, childLogicalExpression, this);
        children.addLast(child);
        child.run();
        if(node != null) Node.connect(context, node, child.node);
        return child.node;
    }

    protected abstract @Mandatory Node onResolveLeave();

    protected void setNode(Node node){
        if(node == null) return;
        if(this.node != null) throw new RuntimeException();
        this.node = node;
        for(MgResolveExpressionTask child : children){
            Node.connect(context, node, child.node);
        }
    }

    public MgExpression createExpression(){
        node.validate();
        return onCreateExpression();
    }

    protected abstract MgExpression onCreateExpression();

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
