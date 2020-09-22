package cz.mg.language.tasks.mg.resolver.command.expression.member;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.basic.MgFunctionExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.member.MgMemberFunctionExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.member.MgMemberVariableExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.MemberNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveMemberNameExpressionTask extends MgResolveMemberExpressionTask {
    @Input
    private final MgLogicalNameCallExpression logicalExpression;

    public MgResolveMemberNameExpressionTask(
        CommandContext context,
        MgLogicalNameCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public MemberNode getNode() {
        return (MemberNode) super.getNode();
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> getLogicalChildren() {
        return null;
    }

    @Override
    protected Node onResolveEnter() {
        return null;
    }

    @Override
    protected Node onResolveLeave() {
        todo;
    }

    @Override
    public MgExpression createExpression() {
        if(todo){
            return new MgMemberVariableExpression(
                variable,
                getOutputInterface().getConnectors().getFirst().getConnection().getConnectionVariable().getOffset()
            );
        }

        if(todo){
            return new MgMemberFunctionExpression(
                function,
                createExpressions(),
                gatherInputOffset(),
                gatherOutputOffset()
            );
        }
    }
}
