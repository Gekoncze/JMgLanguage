package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalUnaryOperatorCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public abstract class MgResolveOperatorExpressionTask extends MgResolveExpressionTask {
    public MgResolveOperatorExpressionTask(CommandContext context, Node parent) {
        super(context, parent);
    }

    public static MgResolveOperatorExpressionTask create(CommandContext context, MgLogicalOperatorCallExpression logicalExpression, Node parent){
        if(logicalExpression instanceof MgLogicalBinaryOperatorCallExpression){
            return new MgResolveBinaryOperatorExpression(context, (MgLogicalBinaryOperatorCallExpression)logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalUnaryOperatorCallExpression){
            return new MgResolveUnaryOperatorExpression(context, (MgLogicalUnaryOperatorCallExpression) logicalExpression, parent);
        }

        throw new LanguageException("Unexpected operator expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }
}
