package cz.mg.language.tasks.mg.resolver.command.expression.member;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveMemberNameExpressionTask extends MgResolveMemberExpressionTask {
    @Input
    private final MgLogicalNameCallExpression logicalExpression;

    public MgResolveMemberNameExpressionTask(CommandContext context, MgLogicalNameCallExpression logicalExpression, Node parent) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected MemberNode getNode() {
        return super.getNode();
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
}
