package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes.BinaryOperatorNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.BinaryOperatorExpressionFilter;


public class MgResolveBinaryOperatorExpression extends MgResolveOperatorExpressionTask {
    @Input
    private final MgLogicalBinaryOperatorCallExpression logicalExpression;

    @Output
    private BinaryOperatorNode node;

    public MgResolveBinaryOperatorExpression(CommandContext context, MgLogicalBinaryOperatorCallExpression logicalExpression, Node parent) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected Node getNode() {
        return node;
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> getLogicalChildren() {
        return new Array<>(logicalExpression.getLeft(), logicalExpression.getRight());
    }

    @Override
    protected void onResolveEnter(InputInterface parentInputInterface) {
        // need to resolve children first
    }

    @Override
    protected void onResolveLeave(InputInterface parentInputInterface, OutputInterface childrenOutputInterface) {
        int replication = childrenOutputInterface.getConnectors().count() / 2;
        List<MgFunction> functions = new List<>();
        for(int r = 0; r < replication; r++){
            BinaryOperatorExpressionFilter filter = new BinaryOperatorExpressionFilter(
                context,
                logicalExpression.getName(),
                parentInputInterface,
                childrenOutputInterface,
                r
            );

            functions.addLast(filter.find());
        }

        node = new BinaryOperatorNode(functions);
    }

    @Override
    protected OutputInterface flatten(List<OutputInterface> outputInterfaces) {
        if(outputInterfaces.count() != 2) throw new RuntimeException();
        if(outputInterfaces.getFirst().getConnectors().count() != outputInterfaces.getLast().getConnectors().count()){
            throw new LanguageException("Unbalanced binary operator.");
        }
        return super.flatten(outputInterfaces);
    }
}
