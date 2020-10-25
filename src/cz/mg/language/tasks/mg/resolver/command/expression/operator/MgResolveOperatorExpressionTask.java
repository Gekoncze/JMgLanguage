package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.LanguageException;
import cz.mg.language.Todo;
import cz.mg.language.entities.mg.Operators;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalUnaryOperatorCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.operator.assignment.MgResolveValueAssignmentExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public abstract class MgResolveOperatorExpressionTask extends MgResolveExpressionTask {
    public MgResolveOperatorExpressionTask(CommandContext context, MgResolveExpressionTask parent) {
        super(context, parent);
    }

    public static MgResolveOperatorExpressionTask create(
        CommandContext context,
        MgLogicalOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ){
        if(logicalExpression instanceof MgLogicalBinaryOperatorCallExpression){
            if(logicalExpression.getName().equals(Operators.ASSIGNMENT)){
                throw new LanguageException("Assignment using = operator is not supported yet. Use &= or $= instead.");
            } else if(logicalExpression.getName().equals(Operators.REFERENCE_ASSIGNMENT)){
//                return new MgResolveReferenceAssignmentExpressionTask(context, (MgLogicalBinaryOperatorCallExpression) logicalExpression, parent);
                new Todo();
                return null;
            } else if(logicalExpression.getName().equals(Operators.VALUE_ASSIGNMENT)){
                return new MgResolveValueAssignmentExpressionTask(context, (MgLogicalBinaryOperatorCallExpression) logicalExpression, parent);
            } else {
                return new MgResolveBinaryOperatorExpression(context, (MgLogicalBinaryOperatorCallExpression)logicalExpression, parent);
            }
        }

        if(logicalExpression instanceof MgLogicalUnaryOperatorCallExpression){
            return create(context, (MgLogicalUnaryOperatorCallExpression) logicalExpression, parent);
        }

        throw new LanguageException("Unexpected operator expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }
}
