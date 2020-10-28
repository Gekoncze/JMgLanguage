package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalLunaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgLunaryOperator;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.operator.MgLunaryOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.expression.operator.LunaryOperatorExpressionFilter;


public class MgResolveLunaryOperatorExpressionTask extends MgResolveUnaryOperatorExpressionTask {
    @Input
    private final MgLogicalLunaryOperatorCallExpression logicalExpression;

    @Output
    private MgLunaryOperatorExpression expression;

    public MgResolveLunaryOperatorExpressionTask(
        CommandContext context,
        MgLogicalLunaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected void onResolve() {
        MgExpression rightChild = resolveChild(logicalExpression.getRight());
        Array<MgLunaryOperator> operators = new Array<>(rightChild.getOutputConnectors().count());
        for(int r = 0; r < operators.count(); r++){
            operators.set(new LunaryOperatorExpressionFilter(
                context,
                logicalExpression.getName(),
                getParent(),
                rightChild,
                r
            ).find(), r);
        }
        expression = new MgLunaryOperatorExpression(operators);
        expression.getExpressions().addLast(rightChild);
    }

    @Override
    public MgExpression getExpression() {
        return expression;
    }
}
