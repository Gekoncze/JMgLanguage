package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.collections.array.Array;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgBinaryOperator;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.operator.MgBinaryOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.expression.operator.BinaryOperatorExpressionFilter;


public class MgResolveBinaryOperatorExpression extends MgResolveOperatorExpressionTask {
    @Input
    private final MgLogicalBinaryOperatorCallExpression logicalExpression;

    @Output
    private MgBinaryOperatorExpression expression;

    public MgResolveBinaryOperatorExpression(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected void onResolve() {
        MgExpression leftChild = resolveChild(logicalExpression.getLeft());
        MgExpression rightChild = resolveChild(logicalExpression.getRight());
        if(leftChild.getOutputConnectors().count() != rightChild.getOutputConnectors().count()){
            throw new LanguageException("Unbalanced binary operator expression.");
        }
        Array<MgBinaryOperator> operators = new Array<>(leftChild.getOutputConnectors().count());
        for(int r = 0; r < operators.count(); r++){
            operators.set(new BinaryOperatorExpressionFilter(
                context,
                logicalExpression.getName(),
                getParent(),
                leftChild,
                rightChild,
                r
            ).find(), r);
        }
        expression = new MgBinaryOperatorExpression(operators);
        expression.getExpressions().addLast(leftChild);
        expression.getExpressions().addLast(rightChild);
    }

    @Override
    public MgExpression getExpression() {
        return expression;
    }
}
