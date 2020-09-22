package cz.mg.language.tasks.mg.resolver.command.expression.member;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalFunctionCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.member.MgMemberFunctionExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.MemberFunctionNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveMemberFunctionExpressionTask extends MgResolveMemberExpressionTask {
    @Input
    private final MgLogicalFunctionCallExpression logicalExpression;

    public MgResolveMemberFunctionExpressionTask(
        CommandContext context,
        MgLogicalFunctionCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public MemberFunctionNode getNode() {
        return (MemberFunctionNode)super.getNode();
    }

    @Override
    protected Node onResolveEnter() {
        todo;
    }

    @Override
    protected void onResolveChildren() {
        onResolveChild(logicalExpression.getExpression());
    }

    @Override
    protected Node onResolveLeave() {
        todo;
    }

    @Override
    public MgExpression createExpression() {
        return new MgMemberFunctionExpression(
            function,
            createExpressions(),
            gatherInputOffset(),
            gatherOutputOffset()
        );
    }
}
