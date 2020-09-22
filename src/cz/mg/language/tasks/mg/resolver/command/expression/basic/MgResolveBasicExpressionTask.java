package cz.mg.language.tasks.mg.resolver.command.expression.basic;

import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalOperatorCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public abstract class MgResolveBasicExpressionTask extends MgResolveExpressionTask {
    public MgResolveBasicExpressionTask(CommandContext context, MgResolveExpressionTask parent) {
        super(context, parent);
    }

    public static MgResolveBasicExpressionTask create(
        CommandContext context,
        MgLogicalCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ){
        if(logicalExpression instanceof MgLogicalNameCallExpression) {
            return new MgResolveNameExpressionTask(context, (MgLogicalNameCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalValueCallExpression){
            return new MgResolveValueExpressionTask(context, (MgLogicalValueCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalFunctionCallExpression){
            return new MgResolveFunctionExpressionTask(context, (MgLogicalFunctionCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalOperatorCallExpression){
            return MgResolveOperatorExpressionTask.create(context, (MgLogicalOperatorCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalGroupCallExpression){
            return new MgResolveGroupExpressionTask(context, (MgLogicalGroupCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalMemberAccessCallExpression){
            return new MgResolveMemberAccessExpression(context, (MgLogicalMemberAccessCallExpression) logicalExpression, parent);
        }

        throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }
}
