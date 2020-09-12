package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalFunctionCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes.FunctionNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.FunctionExpressionFilter;


public class MgResolveFunctionCallExressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalFunctionCallExpression logicalExpression;

    @Output
    private FunctionNode node;

    public MgResolveFunctionCallExressionTask(
        CommandContext context,
        MgLogicalFunctionCallExpression logicalExpression,
        Node parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public FunctionNode getNode() {
        return node;
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> getLogicalChildren() {
        return logicalExpression.getExpressions();
    }

    @Override
    protected void onResolveEnter(InputInterface parentInputInterface) {
        FunctionExpressionFilter expressionFilter = new FunctionExpressionFilter(
            context,
            logicalExpression.getName(),
            parentInputInterface,
            null
        );

        MgFunction function = expressionFilter.findOptional();
        if(function != null){
            node = new FunctionNode(function);
        }
    }

    @Override
    protected void onResolveLeave(InputInterface parentInputInterface, List<OutputInterface> childrenOutputInterface) {
        FunctionExpressionFilter expressionFilter = new FunctionExpressionFilter(
            context,
            logicalExpression.getName(),
            parentInputInterface,
            childrenOutputInterface
        );

        node = new FunctionNode(expressionFilter.find());
    }
}
