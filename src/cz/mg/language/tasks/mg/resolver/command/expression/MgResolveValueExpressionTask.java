package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalValueCallExpression;
import cz.mg.language.entities.mg.runtime.atoms.MgTextObject;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes.ValueNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveValueExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalValueCallExpression logicalExpression;

    @Output
    private ValueNode node;

    public MgResolveValueExpressionTask(CommandContext context, MgLogicalValueCallExpression logicalExpression, Node parent) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected Node getNode() {
        return node;
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> getLogicalChildren() {
        return null;
    }

    @Override
    protected void onResolveEnter(InputInterface parentInputInterface) {
    }

    @Override
    protected void onResolveLeave(InputInterface parentInputInterface, OutputInterface childrenOutputInterface) {
        node = new ValueNode(new MgTextObject(logicalExpression.getValue().toString()));
    }
}
