package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.assignment.MgReferenceAssignmentExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveReferenceAssignmentExpressionTask extends MgResolveAssignmentExpressionTask {
    public MgResolveReferenceAssignmentExpressionTask(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, logicalExpression, parent);
    }

    @Override
    protected void onResolveChildren() {
        onResolveChild(sourceLogicalExpression);
    }

    @Override
    protected @Mandatory Node onResolveLeave() {
        return todo;
    }

    @Override
    protected MgExpression onCreateExpression() {
        List<MgReferenceAssignmentExpression.Replication> replications = new List<>();
        todo;

        return new MgReferenceAssignmentExpression(
            getChildren().getFirst().createExpression(),
            replications
        );
    }
}
