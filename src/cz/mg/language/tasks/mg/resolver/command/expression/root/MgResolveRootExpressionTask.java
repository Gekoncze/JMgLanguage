package cz.mg.language.tasks.mg.resolver.command.expression.root;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.runtime.parts.connection.MgConnection;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public abstract class MgResolveRootExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalCallExpression logicalExpression;

    @Output
    private MgExpression expression;

    public MgResolveRootExpressionTask(CommandContext context, MgLogicalCallExpression logicalExpression) {
        super(context, null);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public MgExpression getExpression() {
        return expression;
    }

    @Override
    protected void onResolve() {
        MgResolveExpressionTask task = MgResolveExpressionTask.create(context, logicalExpression, null);
        task.run();
        expression = task.getExpression();
    }

    public void connect(MgInputConnector inputConnector){
        onConnect(inputConnector);
        expression.validate();
    }

    protected abstract void onConnect(MgInputConnector inputConnector);
}
