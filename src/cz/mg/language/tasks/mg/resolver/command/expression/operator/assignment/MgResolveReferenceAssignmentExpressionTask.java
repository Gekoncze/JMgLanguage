package cz.mg.language.tasks.mg.resolver.command.expression.operator.assignment;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgGroupExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgReferenceAssignmentExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.variable.MgVariableSetExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveReferenceAssignmentExpressionTask extends MgResolveAssignmentExpressionTask {
    @Input
    private final MgLogicalBinaryOperatorCallExpression logicalExpression;

    @Output
    private MgReferenceAssignmentExpression expression;

    public MgResolveReferenceAssignmentExpressionTask(
        CommandContext context,
        MgLogicalBinaryOperatorCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, logicalExpression, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public MgExpression getExpression() {
        return expression;
    }

    @Override
    protected void onResolve() {
        MgExpression leftChild = resolveChild(logicalExpression.getLeft());
        MgExpression rightChild = resolveChild(logicalExpression.getRight());

        List<MgInputConnector> inputConnectors = new List<>();
        getInputConnectors(leftChild, inputConnectors);

        if(inputConnectors.count() != rightChild.getOutputConnectors().count()){
            throw new LanguageException("Unbalanced reference assignment operator expression.");
        }

        expression = new MgReferenceAssignmentExpression();
        expression.getExpressions().addLast(leftChild);
        expression.getExpressions().addLast(rightChild);

        for(int i = 0; i < inputConnectors.count(); i++){
            connect(
                inputConnectors.get(i),
                rightChild.getOutputConnectors().get(i)
            );
        }
    }

    private void getInputConnectors(MgExpression leftChild, List<MgInputConnector> inputConnectors){
        if(leftChild instanceof MgGroupExpression){
            for(MgExpression expression : leftChild.getExpressions()){
                getInputConnectors(expression, inputConnectors);
            }
        } else if(leftChild instanceof MgVariableSetExpression){
            inputConnectors.addLast(leftChild.getInputConnectors().getFirst());
        } else {
            throw new LanguageException("Expected variable set expression for reference assignment, but got " + leftChild.getClass().getSimpleName() + ".");
        }
    }
}
