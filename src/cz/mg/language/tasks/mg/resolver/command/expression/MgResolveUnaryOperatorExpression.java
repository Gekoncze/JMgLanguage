package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalLunaryOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalRunaryOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalUnaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes.UnaryOperatorNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.UnaryOperatorExpressionFilter;


public class MgResolveUnaryOperatorExpression extends MgResolveOperatorExpressionTask {
    @Input
    private final MgLogicalUnaryOperatorCallExpression logicalExpression;

    @Output
    private UnaryOperatorNode node;

    public MgResolveUnaryOperatorExpression(CommandContext context, MgLogicalUnaryOperatorCallExpression logicalExpression, Node parent) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected Node getNode() {
        return node;
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> getLogicalChildren() {
        if(logicalExpression instanceof MgLogicalLunaryOperatorCallExpression){
            return new Array<>(((MgLogicalLunaryOperatorCallExpression) logicalExpression).getRight());
        }

        if(logicalExpression instanceof MgLogicalRunaryOperatorCallExpression){
            return new Array<>(((MgLogicalRunaryOperatorCallExpression) logicalExpression).getLeft());
        }

        throw new LanguageException("Unexpected unary operator expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }

    @Override
    protected void onResolveEnter(InputInterface parentInputInterface) {
        // need to resolve children first
    }

    @Override
    protected void onResolveLeave(InputInterface parentInputInterface, OutputInterface childrenOutputInterface) {
        int replication = childrenOutputInterface.getConnectors().count();
        List<MgFunction> functions = new List<>();
        for(int r = 0; r < replication; r++){
            UnaryOperatorExpressionFilter filter = new UnaryOperatorExpressionFilter(
                context,
                logicalExpression.getName(),
                parentInputInterface,
                childrenOutputInterface,
                r
            );

            functions.addLast(filter.find());
        }

        node = new UnaryOperatorNode(functions);
    }
}
