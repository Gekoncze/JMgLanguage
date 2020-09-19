package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalGroupCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes.GroupNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveGroupExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalGroupCallExpression logicalExpression;

    @Output
    private GroupNode node;

    public MgResolveGroupExpressionTask(CommandContext context, MgLogicalGroupCallExpression logicalExpression, Node parent) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected Node getNode() {
        return node;
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> getLogicalChildren() {
        return logicalExpression.getExpressions();
    }

    @Override
    protected void onResolveEnter(InputInterface parentInputInterface) {
        if(parentInputInterface != null){
            node = new GroupNode(parentInputInterface);
        }
    }

    @Override
    protected void onResolveLeave(InputInterface parentInputInterface, OutputInterface childrenOutputInterface) {
        node = new GroupNode(childrenOutputInterface);
    }
}
