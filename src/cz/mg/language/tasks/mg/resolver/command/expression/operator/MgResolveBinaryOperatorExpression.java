package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.operator.MgBinaryOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveBinaryOperatorExpression extends MgResolveOperatorExpressionTask {
    @Input
    private final MgLogicalBinaryOperatorCallExpression logicalExpression;

    @Output
    private MgBinaryOperatorExpression expression;

    public MgResolveBinaryOperatorExpression(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        ExpectedParentInput parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public MgBinaryOperatorExpression getExpression() {
        return expression;
    }

    @Override
    protected void onResolve() {
        new Todo();
//        List<MgReplication> replications = null;
//
//        if(getParent() != null){
//            replications = createReplications(getParent(), null, null, true);
//        }
//
//        MgExpression leftChild = resolveChild(
//            logicalExpression.getLeft(),
//            replications == null ? null : new MgLeftExpression(replications)
//        );
//
//        MgExpression rightChild = resolveChild(
//            logicalExpression.getRight(),
//            replications == null ? null : new MgRightExpression(replications)
//        );
//
//        if(leftChild.getOutputConnectors().count() != rightChild.getOutputConnectors().count()){
//            throw new LanguageException("Unbalanced binary operator expression.");
//        }
//
//        if(replications == null){
//            replications = createReplications(getParent(), leftChild, rightChild, false);
//        } else {
//            while(replications.count() > leftChild.getOutputConnectors().count()){
//                replications.removeLast();
//            }
//        }
//
//        expression = new MgBinaryOperatorExpression(replications);
//        expression.getExpressions().addLast(leftChild);
//        expression.getExpressions().addLast(rightChild);
//
//        for(int r = 0; r < expression.getReplications().count(); r++){
//            connect(
//                expression.getInputConnectors().get(r * 2),
//                leftChild.getOutputConnectors().get(r)
//            );
//            connect(
//                expression.getInputConnectors().get(r * 2 + 1),
//                rightChild.getOutputConnectors().get(r)
//            );
//        }
    }

//    private List<@Optional MgReplication> createReplications(
//        @Optional MgExpression parent,
//        @Optional MgExpression leftChild,
//        @Optional MgExpression rightChild,
//        @Mandatory boolean optional
//    ){
//        // todo - problem - list of replications has replication optional
//        // todo - what does it mean for user of this as parent input?
//        // todo - he cannot resolve it or what???
//
//        int replicationCount = guessReplicationCount(parent, leftChild, rightChild);
//        List<MgReplication> replications = new List<>();
//        for(int r = 0; r < replicationCount; r++){
//            replications.addLast(createReplication(parent, leftChild, rightChild, r, optional));
//        }
//        return replications;
//    }
//
//    private MgReplication createReplication(
//        @Optional MgExpression parent,
//        @Optional MgExpression leftChild,
//        @Optional MgExpression rightChild,
//        @Mandatory int replication,
//        @Mandatory boolean optional
//    ){
//        MgBinaryOperator operator = new BinaryOperatorExpressionFilter(
//            context,
//            logicalExpression.getName(),
//            parent,
//            leftChild,
//            rightChild,
//            replication
//        ).find(optional);
//
//        if(operator != null){
//            return new MgReplication(operator);
//        } else {
//            return null;
//        }
//    }
//
//    private int guessReplicationCount(
//        @Optional MgExpression parent,
//        @Optional MgExpression leftChild,
//        @Optional MgExpression rightChild
//    ){
//        if(leftChild != null && rightChild != null){
//            return leftChild.getOutputConnectors().count();
//        } else if(parent != null){
//            return ExpressionFilter.destinationInterface(parent).count();
//        } else {
//            throw new RuntimeException();
//        }
//    }
//
//    private static class MgLeftExpression extends MgExpression {
//        public MgLeftExpression(List<MgReplication> replications) {
//            super(
//                new Array<>(new List<>(input(replications))),
//                new Array<>()
//            );
//        }
//
//        private static Clump<MgInputConnector> input(List<MgReplication> replications){
//            return new PartCollection<>(
//                replications,
//                replication -> replication.getInputConnectors().getFirst()
//            );
//        }
//
//        @Override
//        protected void onRun(MgFunctionInstance functionInstance) {
//            throw new RuntimeException();
//        }
//    }
//
//    private static class MgRightExpression extends MgExpression {
//        public MgRightExpression(List<MgReplication> replications) {
//            super(
//                new Array<>(new List<>(input(replications))),
//                new Array<>()
//            );
//        }
//
//        private static Clump<MgInputConnector> input(List<MgReplication> replications){
//            return new PartCollection<>(
//                replications,
//                replication -> replication.getInputConnectors().getLast()
//            );
//        }
//
//        @Override
//        protected void onRun(MgFunctionInstance functionInstance) {
//            throw new RuntimeException();
//        }
//    }
}
