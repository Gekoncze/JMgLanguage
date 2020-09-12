package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalFunctionCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgFunctionExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.FunctionExpressionFilter;


public class MgResolveFunctionCallExressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalFunctionCallExpression logicalExpression;

    @Output
    private MgFunctionExpression expression;

    public MgResolveFunctionCallExressionTask(CommandContext context, MgLogicalFunctionCallExpression logicalExpression, Node parent) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public MgFunctionExpression getExpression() {
        return expression;
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> onResolveEnter() {
        FunctionExpressionFilter expressionFilter = new FunctionExpressionFilter(
            context,
            logicalExpression.getName(),
            parent.getInputInterface(),
            null
        );

        MgFunction function = expressionFilter.findOptional();

        //todo;
        return null;
    }

    @Override
    protected void onResolveLeave() {
        //todo;
    }
}
