package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalLunaryOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalRunaryOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalUnaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public abstract class MgResolveUnaryOperatorExpressionTask extends MgResolveOperatorExpressionTask {
    public MgResolveUnaryOperatorExpressionTask(
        CommandContext context,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
    }

    public static MgResolveOperatorExpressionTask create(
        CommandContext context,
        MgLogicalUnaryOperatorCallExpression logicalExpression,
        MgExpression parent
    ){
        if(logicalExpression instanceof MgLogicalLunaryOperatorCallExpression){
            return new MgResolveLunaryOperatorExpressionTask(context, (MgLogicalLunaryOperatorCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalRunaryOperatorCallExpression){
            return new MgResolveRunaryOperatorExpressionTask(context, (MgLogicalRunaryOperatorCallExpression) logicalExpression, parent);
        }

        throw new LanguageException("Unexpected operator expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }
}
