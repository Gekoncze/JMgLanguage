package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgBinaryOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.BinaryOperatorNode;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.BinaryOperatorExpressionFilter;

import java.util.Iterator;


public class MgResolveBinaryOperatorExpression extends MgResolveOperatorExpressionTask {
    @Input
    private final MgLogicalBinaryOperatorCallExpression logicalExpression;

    public MgResolveBinaryOperatorExpression(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public BinaryOperatorNode getNode() {
        return (BinaryOperatorNode) super.getNode();
    }

    @Override
    protected Node onResolveEnter() {
        return null;
    }

    @Override
    protected void onResolveChildren() {
        onResolveChild(logicalExpression.getLeft());
        onResolveChild(logicalExpression.getRight());
    }

    @Override
    protected Node onResolveLeave() {
        OutputInterface leftOutput = getChildren().getFirst().getOutputInterface();
        OutputInterface rightOutput = getChildren().getLast().getOutputInterface();

        int leftOperandCount = leftOutput.getConnectors().count();
        int rightOperandCount = rightOutput.getConnectors().count();

        if(leftOperandCount != rightOperandCount){
            throw new LanguageException("Unbalanced binary operator.");
        }

        int replication = leftOperandCount;
        List<MgFunction> functions = new List<>();
        for(int r = 0; r < replication; r++){
            BinaryOperatorExpressionFilter filter = new BinaryOperatorExpressionFilter(
                context,
                logicalExpression.getName(),
                getParentInputInterface(),
                leftOutput,
                rightOutput,
                r
            );

            functions.addLast(filter.find());
        }

        return new BinaryOperatorNode(functions);
    }

    @Override
    public MgExpression onCreateExpression() {
        List<MgBinaryOperatorExpression.Replication> replications = new List<>();

        Array<MgLocalVariable>[] input = gatherInputOffset();
        Array<MgLocalVariable> output = gatherOutputOffset();

        int i = 0;
        for(MgFunction function : getNode().getFunctions()){
            replications.addLast(
                new MgBinaryOperatorExpression.Replication(
                    function,
                    input[0].get(i),
                    input[1].get(i),
                    output.get(i)
                )
            );
            i++;
        }

        if(getChildren().count() != 2) throw new RuntimeException();
        return new MgBinaryOperatorExpression(
            getChildren().getFirst().onCreateExpression(),
            getChildren().getLast().onCreateExpression(),
            replications
        );
    }

    private Array<MgLocalVariable>[] gatherInputOffset(){
        Array<MgLocalVariable>[] offset = new Array[2];
        offset[0] = new Array<>(getNode().getFunctions().count());
        offset[1] = new Array<>(getNode().getFunctions().count());
        Iterator<InputConnector> iterator = getInputInterface().getConnectors().iterator();
        for(int i = 0; i < getNode().getFunctions().count(); i++){
            offset[0].set(iterator.next().getConnection().getConnectionVariable(), i);
        }
        for(int i = 0; i < getNode().getFunctions().count(); i++){
            offset[1].set(iterator.next().getConnection().getConnectionVariable(), i);
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
}
