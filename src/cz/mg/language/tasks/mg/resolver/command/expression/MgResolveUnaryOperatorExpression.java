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
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes.BinaryOperatorNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveUnaryOperatorExpression extends MgResolveOperatorExpressionTask {
    @Input
    private final MgLogicalUnaryOperatorCallExpression logicalExpression;

    @Output
    private BinaryOperatorNode node;

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
        //todo;
    }

    @Override
    protected void onResolveLeave(InputInterface parentInputInterface, OutputInterface childrenOutputInterface) {
        //todo;
    }
}
