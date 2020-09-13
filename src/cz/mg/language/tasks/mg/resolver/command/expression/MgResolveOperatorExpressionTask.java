package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalOperatorCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveOperatorExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalOperatorCallExpression logicalExpression;

    @Output
    private Node node;

    public MgResolveOperatorExpressionTask(CommandContext context, MgLogicalOperatorCallExpression logicalExpression, Node parent) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected Node getNode() {
        return node;
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> getLogicalChildren() {
        return null;
    }

    @Override
    protected void onResolveEnter(InputInterface parentInputInterface) {
        OperatorExpressionFilter filter = new ?;
    }

    @Override
    protected void onResolveLeave(InputInterface parentInputInterface, List<OutputInterface> childrenOutputInterface) {

    }
}
