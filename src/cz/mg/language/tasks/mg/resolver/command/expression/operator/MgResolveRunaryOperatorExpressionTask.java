package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalRunaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgRunaryOperator;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.operator.MgRunaryOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.expression.operator.RunaryOperatorExpressionFilter;


public class MgResolveRunaryOperatorExpressionTask extends MgResolveUnaryOperatorExpressionTask {
    @Input
    private final MgLogicalRunaryOperatorCallExpression logicalExpression;

    @Output
    private MgRunaryOperatorExpression expression;

    public MgResolveRunaryOperatorExpressionTask(
        CommandContext context,
        MgLogicalRunaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected void onResolve() {
        MgExpression leftChild = resolveChild(logicalExpression.getLeft());
        Array<MgRunaryOperator> operators = new Array<>(leftChild.getOutputConnectors().count());
        for(int r = 0; r < operators.count(); r++){
            operators.set(new RunaryOperatorExpressionFilter(
                context,
                logicalExpression.getName(),
                getParent(),
                leftChild,
                r
            ).find(), r);
        }
        expression = new MgRunaryOperatorExpression(operators);
    }

    @Override
    public MgExpression getExpression() {
        return expression;
    }
}
