package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalRunaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgRunaryOperator;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.operator.MgRunaryOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.expression.operator.RunaryOperatorExpressionFilter;
import static cz.mg.language.entities.mg.runtime.parts.expressions.operator.MgRunaryOperatorExpression.MgReplication;


public class MgResolveRunaryOperatorExpressionTask extends MgResolveUnaryOperatorExpressionTask {
    @Input
    private final MgLogicalRunaryOperatorCallExpression logicalExpression;

    @Output
    private MgRunaryOperatorExpression expression;

    public MgResolveRunaryOperatorExpressionTask(
        CommandContext context,
        MgLogicalRunaryOperatorCallExpression logicalExpression,
        ExpectedParentInput parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected void onResolve() {
        MgExpression leftChild = resolveChild(logicalExpression.getLeft(), getExpectedInput());
        int replicationCount = leftChild.getOutputConnectors().count();

        List<MgReplication> replications = new List<>();
        for(int r = 0; r < replicationCount; r++){
            replications.addLast(new MgReplication(new RunaryOperatorExpressionFilter(
                context,
                logicalExpression.getName(),
                getParent(),
                leftChild,
                r
            ).find()));
        }

        expression = new MgRunaryOperatorExpression(replications, leftChild);
    }
    
        private ExpectedParentInput getExpectedInput(){
        if(getParent() != null){
            ExpectedParentInput expectedInput = new ExpectedParentInput();
            for(int r = 0; r < getParent().getDatatypes().count(); r++){
                MgRunaryOperator operator = new RunaryOperatorExpressionFilter(
                    context,
                    logicalExpression.getName(),
                    getParent(),
                    null,
                    r
                ).findOptional();
                expectedInput.getDatatypes().addLast(
                    operator != null
                        ? operator.getInputVariables().getFirst().getDatatype()
                        : null
                );
            }
            return expectedInput;
        } else {
            return null;
        }
    }

    @Override
    public MgExpression getExpression() {
        return expression;
    }
}
