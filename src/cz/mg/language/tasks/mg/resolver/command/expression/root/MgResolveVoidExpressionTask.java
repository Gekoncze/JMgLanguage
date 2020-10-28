package cz.mg.language.tasks.mg.resolver.command.expression.root;

import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveVoidExpressionTask extends MgResolveRootExpressionTask {
    public MgResolveVoidExpressionTask(CommandContext context, MgLogicalCallExpression logicalExpression) {
        super(context, logicalExpression);
    }

    @Override
    protected void onConnect(MgInputConnector inputConnector) {
        connectNoOutputExpression(getExpression());
    }

    protected static void connectNoOutputExpression(MgExpression expression){
        if(expression.getOutputConnectors().count() == 0){
            expression.validate();
        } else {
            throw new LanguageException("Expression must have no output.");
        }
    }
}
