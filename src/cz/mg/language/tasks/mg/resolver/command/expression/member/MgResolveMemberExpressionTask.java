package cz.mg.language.tasks.mg.resolver.command.expression.member;

import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalFunctionCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public abstract class MgResolveMemberExpressionTask extends MgResolveExpressionTask {
    public MgResolveMemberExpressionTask(CommandContext context, Node parent) {
        super(context, parent);
    }

    @Override
    protected MgResolveExpressionTask onCreateChildResolver(CommandContext context, MgLogicalCallExpression logicalExpression, Node parent) {
        return create(context, logicalExpression, parent);
    }

    public static MgResolveMemberExpressionTask create(CommandContext context, MgLogicalCallExpression logicalExpression, Node parent){
        if(logicalExpression instanceof MgLogicalNameCallExpression) {
            return new MgResolveMemberNameExpressionTask(context, (MgLogicalNameCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalFunctionCallExpression) {
            return new MgResolveMemberFunctionExpressionTask(context, (MgLogicalFunctionCallExpression) logicalExpression, parent);
        }

        throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }
}
