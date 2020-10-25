package cz.mg.language.tasks.mg.resolver.command.expression.operator.assignment;

import cz.mg.language.Todo;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveValueAssignmentExpressionTask extends MgResolveAssignmentExpressionTask {
    public MgResolveValueAssignmentExpressionTask(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, logicalExpression, parent);
        new Todo();
    }

    @Override
    protected void onResolve() {
        new Todo();
    }

    @Override
    public MgExpression getExpression() {
        new Todo();
        return null;
    }

//    @Override
//    protected MgExpression onCreateExpression() {
//        List<MgValueAssignmentExpression.Replication> replications = new List<>();
//
//        OutputInterface leftOutputInterface = voidTask.getChildren().getFirst().getOutputInterface();
//        OutputInterface rightOutputInterface = getChildren().getFirst().getOutputInterface();
//
//        for(int replication = 0; replication < leftOutputInterface.getConnectors().count(); replication++){
//            ValueAssignmentOperatorExpressionFilter filter = new ValueAssignmentOperatorExpressionFilter(
//                context,
//                Operators.VALUE_ASSIGNMENT,
//                getParentInputConnectors(),
//                leftOutputInterface,
//                rightOutputInterface,
//                replication
//            );
//
//            replications.addLast(new MgValueAssignmentExpression.Replication(
//                filter.find(),
//                leftOutputInterface.getConnectors().get(replication).getConnection().getConnectionVariable(),
//                rightOutputInterface.getConnectors().get(replication).getConnection().getConnectionVariable()
//            ));
//        }
//
//        return new MgValueAssignmentExpression(
//            getChildren().getFirst().createExpression(),
//            voidTask.createExpression(),
//            replications
//        );
//    }
}
