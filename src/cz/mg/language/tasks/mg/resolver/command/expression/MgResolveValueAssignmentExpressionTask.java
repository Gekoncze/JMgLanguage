package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.assignment.MgValueAssignmentExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.ValueAssignmentNode;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveValueAssignmentExpressionTask extends MgResolveAssignmentExpressionTask {
    public MgResolveValueAssignmentExpressionTask(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, logicalExpression, parent);
    }

    @Override
    protected @Mandatory Node onResolveLeave() {
        return new ValueAssignmentNode(variables);
    }

    @Override
    protected MgExpression onCreateExpression() {
        List<MgValueAssignmentExpression.Replication> replications = new List<>();
        for(){
            replications.addLast(new MgValueAssignmentExpression.Replication(
                destinationExpression,
                function,
                leftInput,
                rightInput
            ));
        }

        return new MgValueAssignmentExpression(
            getChildren().getFirst().createExpression(),
            replications
        );
    }
}
