package cz.mg.language.tasks.mg.resolver.command.expression.member;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalFunctionCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.MemberFunctionNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveMemberFunctionExpressionTask extends MgResolveMemberExpressionTask {
    @Input
    private final MgLogicalFunctionCallExpression logicalExpression;

    public MgResolveMemberFunctionExpressionTask(CommandContext context, MgLogicalFunctionCallExpression logicalExpression, Node parent) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public MemberFunctionNode getNode() {
        return (MemberFunctionNode)super.getNode();
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> getLogicalChildren() {
        return todo;
    }

    @Override
    protected void onResolveEnter(InputInterface parentInputInterface) {
        todo;
    }

    @Override
    protected void onResolveLeave(InputInterface parentInputInterface, List<OutputInterface> childrenOutputInterface) {
        todo;
    }
}
