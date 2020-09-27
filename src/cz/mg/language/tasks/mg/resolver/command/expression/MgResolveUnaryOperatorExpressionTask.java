package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalLunaryOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalRunaryOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalUnaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.components.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgUnaryOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.UnaryOperatorNode;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.UnaryOperatorExpressionFilter;

import java.util.Iterator;


public abstract class MgResolveUnaryOperatorExpressionTask extends MgResolveOperatorExpressionTask {
    @Input
    private final MgLogicalUnaryOperatorCallExpression logicalExpression;

    public MgResolveUnaryOperatorExpressionTask(
        CommandContext context,
        MgLogicalUnaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public UnaryOperatorNode getNode() {
        return (UnaryOperatorNode) super.getNode();
    }

    @Override
    protected Node onResolveEnter() {
        return null;
    }

    protected List<MgFunction> resolveFunctions(){
        OutputInterface childrenOutputInterface = getChildren().getFirst().getOutputInterface();
        int replication = childrenOutputInterface.getConnectors().count();
        List<MgFunction> functions = new List<>();
        for(int r = 0; r < replication; r++){
            UnaryOperatorExpressionFilter filter = new UnaryOperatorExpressionFilter(
                context,
                logicalExpression.getName(),
                getParentInputInterface(),
                childrenOutputInterface,
                r
            );

            functions.addLast(filter.find());
        }
        return functions;
    }

    protected MgExpression createChildExpression(){
        return getChildren().getFirst().onCreateExpression();
    }

    protected List<MgUnaryOperatorExpression.Replication> createReplications(){
        List<MgUnaryOperatorExpression.Replication> replications = new List<>();

        Array<MgLocalVariable> input = gatherInputOffset();
        Array<MgLocalVariable> output = gatherOutputOffset();

        int i = 0;
        for(MgFunction function : getNode().getFunctions()){
            replications.addLast(
                new MgUnaryOperatorExpression.Replication(
                    function,
                    input.get(i),
                    output.get(i)
                )
            );
            i++;
        }

        return replications;
    }

    private Array<MgLocalVariable> gatherInputOffset(){
        Array<MgLocalVariable> offset = new Array<>(getNode().getFunctions().count());
        Iterator<InputConnector> iterator = getInputInterface().getConnectors().iterator();
        for(int i = 0; i < getNode().getFunctions().count(); i++){
            offset.set(iterator.next().getConnection().getConnectionVariable(), i);
        }
        return offset;
    }

    private Array<MgLocalVariable> gatherOutputOffset(){
        Array<MgLocalVariable> offset = new Array<>(getNode().getFunctions().count());
        Iterator<OutputConnector> iterator = getOutputInterface().getConnectors().iterator();
        for(int i = 0; i < getNode().getFunctions().count(); i++){
            offset.set(iterator.next().getConnection().getConnectionVariable(), i);
        }
        return offset;
    }

    public static MgResolveOperatorExpressionTask create(
        CommandContext context,
        MgLogicalUnaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ){
        if(logicalExpression instanceof MgLogicalLunaryOperatorCallExpression){
            return new MgResolveLunaryOperatorExpressionTask(context, (MgLogicalLunaryOperatorCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalRunaryOperatorCallExpression){
            return new MgResolveRunaryOperatorExpressionTask(context, (MgLogicalRunaryOperatorCallExpression) logicalExpression, parent);
        }

        throw new LanguageException("Unexpected operator expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }
}
