package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes.BinaryOperatorNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


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
        //todo;
    }

    @Override
    protected void onResolveLeave(InputInterface parentInputInterface, OutputInterface childrenOutputInterface) {
        //todo;
    }
}
