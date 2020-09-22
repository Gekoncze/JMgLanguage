package cz.mg.language.tasks.mg.resolver.command.expression.basic;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalMemberAccessCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.basic.MgMemberAccessExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.member.MgMemberExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.member.MgResolveMemberExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.MemberAccessNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveMemberAccessExpression extends MgResolveBasicExpressionTask {
    @Input
    private final MgLogicalMemberAccessCallExpression logicalExpression;

    public MgResolveMemberAccessExpression(
        CommandContext context,
        MgLogicalMemberAccessCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public MemberAccessNode getNode() {
        return (MemberAccessNode) super.getNode();
    }

    @Override
    protected Node onResolveEnter() {
        return null;
    }

    @Override
    protected void onResolveChildren() {
        onResolveChild(logicalExpression.getLeft());
        onResolveChild(logicalExpression.getRight());
    }

    @Override
    protected Node onResolveLeave() {
        return new MemberAccessNode(
            getChildren().getFirst().getOutputInterface(),
            getChildren().getLast().getOutputInterface()
        );
    }

    @Override
    protected MgResolveExpressionTask onCreateChildResolver(
        CommandContext context,
        MgLogicalCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        if(logicalExpression == this.logicalExpression.getLeft()){
            return MgResolveMemberAccessibleExpressionTask.create(context, logicalExpression, parent);
        }

        if(logicalExpression == this.logicalExpression.getRight()){
            return MgResolveMemberExpressionTask.create(someContext, logicalExpression, parent);
        }

        throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " for member access resolve.");
    }

    @Override
    public MgExpression createExpression() {
        if(getChildren().count() != 2) throw new RuntimeException();
        if(getInputInterface().getConnectors().count() != 2) throw new RuntimeException();
        if(getOutputInterface().getConnectors().count() != 1) throw new RuntimeException();
        MgExpression leftExpression = getChildren().getFirst().createExpression();
        MgExpression rightExpression = getChildren().getLast().createExpression();
        if(!(rightExpression instanceof MgMemberExpression)) throw new LanguageException("Illegal member access.");
        return new MgMemberAccessExpression(
            asBasicExpression(leftExpression),
            asMemberExpression(rightExpression),
            getInputInterface().getConnectors().getFirst().getConnection().getConnectionVariable(),
            getInputInterface().getConnectors().getLast().getConnection().getConnectionVariable(),
            getOutputInterface().getConnectors().getFirst().getConnection().getConnectionVariable()
        );
    }
}
