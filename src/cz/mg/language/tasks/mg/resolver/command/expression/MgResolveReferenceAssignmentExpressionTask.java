//package cz.mg.language.tasks.mg.resolver.command.expression;
//
//import cz.mg.collections.list.List;
//import cz.mg.language.LanguageException;
//import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
//import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
//import cz.mg.language.entities.mg.runtime.parts.expressions.MgGroupExpression;
//import cz.mg.language.entities.mg.runtime.parts.expressions.assignment.MgReferenceAssignmentExpression;
//import cz.mg.language.entities.mg.runtime.parts.expressions.variable.MgVariableExpression;
//import cz.mg.language.tasks.mg.resolver.context.CommandContext;
//import static cz.mg.language.entities.mg.runtime.parts.expressions.assignment.MgReferenceAssignmentExpression.Replication;
//
//
//public class MgResolveReferenceAssignmentExpressionTask extends MgResolveAssignmentExpressionTask {
//    public MgResolveReferenceAssignmentExpressionTask(
//        CommandContext context,
//        MgLogicalBinaryOperatorCallExpression logicalExpression,
//        MgResolveExpressionTask parent
//    ) {
//        super(context, logicalExpression, parent);
//    }
//
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
//}
