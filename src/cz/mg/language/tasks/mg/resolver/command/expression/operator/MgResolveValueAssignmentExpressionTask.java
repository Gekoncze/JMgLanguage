package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.collections.array.Array;
import cz.mg.language.LanguageException;
import cz.mg.language.Todo;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgBinaryOperator;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.operator.MgValueAssignmentOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.expression.operator.ValueAssignmentOperatorExpressionFilter;


public class MgResolveValueAssignmentExpressionTask extends MgResolveOperatorExpressionTask {
    @Input
    private final MgLogicalBinaryOperatorCallExpression logicalExpression;

    @Output
    private MgValueAssignmentOperatorExpression expression;

    public MgResolveValueAssignmentExpressionTask(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        ExpectedParentInput parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected void onResolve() {
        new Todo();
//        MgExpression leftChild = resolveChild(logicalExpression.getLeft());
//        MgExpression rightChild = resolveChild(logicalExpression.getRight());
//        if(leftChild.getOutputConnectors().count() != rightChild.getOutputConnectors().count()){
//            throw new LanguageException("Unbalanced value assignment operator expression.");
//        }
//
//        Array<MgBinaryOperator> operators = new Array<>(leftChild.getOutputConnectors().count());
//        for(int r = 0; r < operators.count(); r++){
//            operators.set(new ValueAssignmentOperatorExpressionFilter(
//                context,
//                logicalExpression.getName(),
//                getParent(),
//                leftChild,
//                rightChild,
//                r
//            ).find(), r);
//        }
//
//        expression = new MgValueAssignmentOperatorExpression(operators);
//        expression.getExpressions().addLast(leftChild);
//        expression.getExpressions().addLast(rightChild);
//
//        for(int r = 0; r < expression.getReplications().count(); r++){
//            connect(
//                expression.getInputConnectors().get(r * 2),
//                leftChild.getOutputConnectors().get(r)
//            );
//            connect(
//                expression.getInputConnectors().get(r * 2 + 1),
//                rightChild.getOutputConnectors().get(r)
//            );
//        }
    }

    @Override
    public MgExpression getExpression() {
        return expression;
    }
}
