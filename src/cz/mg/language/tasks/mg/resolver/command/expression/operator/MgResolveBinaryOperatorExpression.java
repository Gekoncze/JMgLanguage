package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.language.Todo;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveBinaryOperatorExpression extends MgResolveOperatorExpressionTask {
    @Input
    private final MgLogicalBinaryOperatorCallExpression logicalExpression;

    public MgResolveBinaryOperatorExpression(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
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
//    public BinaryOperatorNode getNode() {
//        return (BinaryOperatorNode) super.getNode();
//    }
//
//    @Override
//    protected Node onResolveEnter() {
//        return null;
//    }
//
//    @Override
//    protected void onResolveChildren() {
//        onResolveChild(logicalExpression.getLeft());
//        onResolveChild(logicalExpression.getRight());
//    }
//
//    @Override
//    protected Node onResolveLeave() {
//        OutputInterface leftOutput = getChildren().getFirst().getOutputInterface();
//        OutputInterface rightOutput = getChildren().getLast().getOutputInterface();
//
//        int leftOperandCount = leftOutput.getConnectors().count();
//        int rightOperandCount = rightOutput.getConnectors().count();
//
//        if(leftOperandCount != rightOperandCount){
//            throw new LanguageException("Unbalanced binary operator.");
//        }
//
//        int replication = leftOperandCount;
//        List<MgFunction> functions = new List<>();
//        for(int r = 0; r < replication; r++){
//            BinaryOperatorExpressionFilter filter = new BinaryOperatorExpressionFilter(
//                context,
//                logicalExpression.getName(),
//                getParentInputConnectors(),
//                leftOutput,
//                rightOutput,
//                r
//            );
//
//            functions.addLast(filter.find());
//        }
//
//        return new BinaryOperatorNode(functions);
//    }
//
//    @Override
//    public MgExpression onCreateExpression() {
//        List<MgBinaryOperatorExpression.Replication> replications = new List<>();
//
//        Array<MgFunctionVariable>[] input = gatherInputOffset();
//        Array<MgFunctionVariable> output = gatherOutputOffset();
//
//        int i = 0;
//        for(MgFunction function : getNode().getFunctions()){
//            replications.addLast(
//                new MgBinaryOperatorExpression.Replication(
//                    function,
//                    input[0].get(i),
//                    input[1].get(i),
//                    output.get(i)
//                )
//            );
//            i++;
//        }
//
//        if(getChildren().count() != 2) throw new RuntimeException();
//        return new MgBinaryOperatorExpression(
//            getChildren().getFirst().onCreateExpression(),
//            getChildren().getLast().onCreateExpression(),
//            replications
//        );
//    }
//
//    private Array<MgFunctionVariable>[] gatherInputOffset(){
//        Array<MgFunctionVariable>[] offset = new Array[2];
//        offset[0] = new Array<>(getNode().getFunctions().count());
//        offset[1] = new Array<>(getNode().getFunctions().count());
//        Iterator<InputConnector> iterator = getInputConnectors().getConnectors().iterator();
//        for(int i = 0; i < getNode().getFunctions().count(); i++){
//            offset[0].set(iterator.next().getConnection().getConnectionVariable(), i);
//        }
//        for(int i = 0; i < getNode().getFunctions().count(); i++){
//            offset[1].set(iterator.next().getConnection().getConnectionVariable(), i);
//        }
//        return offset;
//    }
//
//    private Array<MgFunctionVariable> gatherOutputOffset(){
//        Array<MgFunctionVariable> offset = new Array<>(getNode().getFunctions().count());
//        Iterator<OutputConnector> iterator = getOutputConnectors().getConnectors().iterator();
//        for(int i = 0; i < getNode().getFunctions().count(); i++){
//            offset.set(iterator.next().getConnection().getConnectionVariable(), i);
//        }
//        return offset;
//    }
}
