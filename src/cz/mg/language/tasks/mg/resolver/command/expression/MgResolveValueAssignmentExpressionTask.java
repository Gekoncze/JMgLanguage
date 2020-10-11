package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.Operators;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.assignment.MgValueAssignmentExpression;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.ValueAssignmentOperatorExpressionFilter;


public class MgResolveValueAssignmentExpressionTask extends MgResolveAssignmentExpressionTask {
    public MgResolveValueAssignmentExpressionTask(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, logicalExpression, parent);
    }

    @Override
    protected MgExpression onCreateExpression() {
        List<MgValueAssignmentExpression.Replication> replications = new List<>();
        for(){
            ValueAssignmentOperatorExpressionFilter filter = new ValueAssignmentOperatorExpressionFilter(
                context,
                Operators.VALUE_ASSIGNMENT,
                getParentInputInterface(),
                leftChildrenOutputInterface,
                rightChildrenOutputInterface,
                replication
            );

            replications.addLast(new MgValueAssignmentExpression.Replication(
                filter.find(),
                leftInput,
                rightInput
            ));
        }

        return new MgValueAssignmentExpression(
            getChildren().getFirst().createExpression(),
            voidTask.createExpression(),
            replications
        );
    }
}
