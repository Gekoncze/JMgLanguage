package cz.mg.language.tasks.mg.resolver.command.expression.root;

import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.runtime.parts.connection.MgConnection;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveNonVoidExpressionTask extends MgResolveRootExpressionTask {
    public MgResolveNonVoidExpressionTask(CommandContext context, MgLogicalCallExpression logicalExpression) {
        super(context, logicalExpression);
    }

    @Override
    protected void onConnect(MgInputConnector inputConnector) {
        connectSingleOutputExpression(context, inputConnector, getExpression());
    }

    protected static void connectSingleOutputExpression(
        CommandContext context,
        MgInputConnector inputConnector,
        MgExpression expression
    ){
        MgConnection.connect(
            inputConnector,
            context.getVariableHelper().nextExpressionVariable(inputConnector.getDatatype()),
            getSingleOutputConnector(expression)
        );
        expression.validate();
    }

    protected static MgOutputConnector getSingleOutputConnector(MgExpression expression){
        if(expression.getOutputConnectors().count() == 1){
            return expression.getOutputConnectors().getFirst();
        } else {
            throw new LanguageException("Expression must have single output.");
        }
    }
}
