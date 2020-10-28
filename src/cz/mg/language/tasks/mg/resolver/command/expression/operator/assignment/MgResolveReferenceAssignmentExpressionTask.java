package cz.mg.language.tasks.mg.resolver.command.expression.operator.assignment;

import cz.mg.language.Todo;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
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
    public MgExpression getExpression() {
        new Todo();
        return null;
    }

    @Override
    protected void onResolve() {
        new Todo();
    }

//    @Override
//    protected MgExpression onCreateExpression() {
//        Iterator<InputConnector> inputConnectors = getInputConnectors().getConnectors().iterator();
//        return new MgReferenceAssignmentExpression(
//            getChildren().getFirst().createExpression(),
//            createReplications(new List<>(), voidTask.createExpression(), inputConnectors)
//        );
//    }
//
//    private List<Replication> createReplications(
//        List<Replication> replications,
//        MgExpression expression,
//        Iterator<InputConnector> inputConnectors
//    ){
//        if(expression instanceof MgVariableExpression){
//            replications.addLast(new Replication(
//                (MgVariableExpression) expression,
//                inputConnectors.next().getConnection().getConnectionVariable()
//            ));
//        } else if(expression instanceof MgGroupExpression){
//            for(MgExpression childExpression : ((MgGroupExpression) expression).getExpressions()){
//                createReplications(replications, childExpression, inputConnectors);
//            }
//        } else {
//            throw new LanguageException("Cannot assign reference to " + expression.getClass().getSimpleName() + ".");
//        }
//        return replications;
//    }
}
