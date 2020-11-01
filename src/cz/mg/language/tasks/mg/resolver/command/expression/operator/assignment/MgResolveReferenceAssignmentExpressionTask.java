package cz.mg.language.tasks.mg.resolver.command.expression.operator.assignment;

import cz.mg.language.LanguageException;
import cz.mg.language.Todo;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgReferenceAssignmentExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveReferenceAssignmentExpressionTask extends MgResolveAssignmentExpressionTask {
    @Input
    private final MgLogicalBinaryOperatorCallExpression logicalExpression;

    @Output
    private MgReferenceAssignmentExpression expression;

    public MgResolveReferenceAssignmentExpressionTask(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        ExpectedParentInput parent
    ) {
        super(context, logicalExpression, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public MgExpression getExpression() {
        return expression;
    }

    @Override
    protected void onResolve() {
        new Todo();
//        MgExpression leftChild = resolveChild(logicalExpression.getLeft());
//        MgExpression rightChild = resolveChild(logicalExpression.getRight());
//
//
//
//        if(inputConnectors.count() != rightChild.getOutputConnectors().count()){
//            throw new LanguageException("Unbalanced reference assignment operator expression.");
//        }
//
//        expression = new MgReferenceAssignmentExpression();
//        expression.getExpressions().addLast(leftChild);
//        expression.getExpressions().addLast(rightChild);
//

    }
}
