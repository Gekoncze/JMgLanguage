package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalFunctionCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalValueCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalOperatorCallExpression;
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
    private final Node parent;

    @Subtask
    private final List<MgResolveExpressionTask> subtasks = new List<>();

    public MgResolveExpressionTask(CommandContext context, Node parent) {
        this.context = context;
        this.parent = parent;
    }

    protected abstract Node getNode();
    protected abstract ReadableCollection<MgLogicalCallExpression> getLogicalChildren();

    @Override
    protected final void onRun() {
        context.getVariableHelper().sink();

        InputInterface parentInputInterface = parent == null ? null : parent.getInputInterface();
        onResolveEnter(parentInputInterface);

        List<Node> children = new List<>();
        List<OutputInterface> childrenOutputInterface = new List<>();

        if(getLogicalChildren() != null){
            for(MgLogicalCallExpression logicalChild : getLogicalChildren()){
                Node child = onResolveChild(logicalChild);

                if(getNode() != null){
                    getNode().getInput().addLast(child);
                    connect(getNode(), child);
                } else {
                    children.addLast(child);
                    childrenOutputInterface.addLast(child.getOutputInterface());
                }
            }
        }

        if(getNode() == null){
            onResolveLeave(parentInputInterface, flatten(childrenOutputInterface));

            for(Node child : children){
                getNode().getInput().addLast(child);
                connect(getNode(), child);
            }
        }

        context.getVariableHelper().raise();
    }

    private static OutputInterface flatten(List<OutputInterface> outputInterfaces){
        List<OutputConnector> outputInterfaceConnectors = new List<>();
        for(OutputInterface outputInterface : outputInterfaces){
            for(OutputConnector outputConnector : outputInterface.getConnectors()){
                outputInterfaceConnectors.addLast(outputConnector);
            }
        }
        return new OutputInterface(new Array<>(outputInterfaceConnectors));
    }

    protected abstract void onResolveEnter(InputInterface parentInputInterface);

    protected Node onResolveChild(MgLogicalCallExpression child){
        subtasks.addLast(create(context, child, getNode()));
        subtasks.getLast().run();
        return subtasks.getLast().getNode();
    }

    protected abstract void onResolveLeave(InputInterface parentInputInterface, OutputInterface childrenOutputInterface);

    public static MgResolveExpressionTask create(CommandContext context, MgLogicalCallExpression logicalExpression, Node parent){
        if(logicalExpression instanceof MgLogicalNameCallExpression) {
            return new MgResolveNameExpressionTask(context, (MgLogicalNameCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalValueCallExpression){
            return new MgResolveValueExpressionTask(context, (MgLogicalValueCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalFunctionCallExpression){
            return new MgResolveFunctionExpressionTask(context, (MgLogicalFunctionCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalOperatorCallExpression){
            return MgResolveOperatorExpressionTask.create(context, (MgLogicalOperatorCallExpression) logicalExpression, parent);
        }

        throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }

    private void connect(Node parent, Node child){
        Node.connect(context.getVariableHelper(), parent, child);
    }
}
