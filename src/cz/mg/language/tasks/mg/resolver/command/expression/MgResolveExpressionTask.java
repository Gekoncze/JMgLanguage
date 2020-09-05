package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalFunctionCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalValueCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public abstract class MgResolveExpressionTask extends MgResolverTask {
    @Input
    protected final CommandContext context;

    @Input
    protected final Node parent;

    @Part
    private final Node node = new Node();

    @Subtask
    private final List<MgResolveExpressionTask> subtasks = new List<>();

    public MgResolveExpressionTask(CommandContext context, Node parent) {
        this.context = context;
        this.parent = parent;
    }

    public abstract MgExpression getExpression();

    @Override
    protected final void onRun() {
        context.getVariableHelper().sink();

        ReadableCollection<MgLogicalCallExpression> logicalChildren = onResolveEnter();

        List<Node> children = new List<>();
        for(MgLogicalCallExpression logicalChild : logicalChildren){
            Node child = onResolveChild(logicalChild);
            children.addLast(child);
            if(node.getInputInterface() != null) connect(node, child);
        }

        onResolveLeave();
        for(Node child : children) connect(node, child);

        context.getVariableHelper().raise();
    }

    protected abstract ReadableCollection<MgLogicalCallExpression> onResolveEnter();

    protected Node onResolveChild(MgLogicalCallExpression child){
        subtasks.addLast(create(context, child, node));
        subtasks.getLast().run();
        return subtasks.getLast().node;
    }

    protected abstract void onResolveLeave();

    public static MgResolveExpressionTask create(CommandContext context, MgLogicalCallExpression logicalExpression, Node parent){
        if(logicalExpression instanceof MgLogicalNameCallExpression) {
            return new MgResolveNameExpressionTask(context, (MgLogicalNameCallExpression) logicalExpression, parent);
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

    private void connect(Node parent, Node child){
        Node.connect(context.getVariableHelper(), parent, child);
    }
}
