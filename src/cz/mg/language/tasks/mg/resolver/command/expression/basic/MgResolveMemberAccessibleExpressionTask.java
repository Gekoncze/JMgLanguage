package cz.mg.language.tasks.mg.resolver.command.expression.basic;

import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalFunctionCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public abstract class MgResolveMemberAccessibleExpressionTask extends MgResolveBasicExpressionTask {
    public MgResolveMemberAccessibleExpressionTask(CommandContext context, MgResolveExpressionTask parent) {
        super(context, parent);
    }

    public static MgResolveMemberAccessibleExpressionTask create(
        CommandContext context,
        MgLogicalCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ){
        if(logicalExpression instanceof MgLogicalNameCallExpression) {
            return new MgResolveNameExpressionTask(context, (MgLogicalNameCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalFunctionCallExpression){
            return new MgResolveFunctionExpressionTask(context, (MgLogicalFunctionCallExpression) logicalExpression, parent);
        }

        throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }
}
