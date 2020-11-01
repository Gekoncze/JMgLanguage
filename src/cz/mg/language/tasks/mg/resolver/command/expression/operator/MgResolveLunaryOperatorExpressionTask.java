package cz.mg.language.tasks.mg.resolver.command.expression.operator;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalLunaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.operator.MgLunaryOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.expression.operator.LunaryOperatorExpressionFilter;
import static cz.mg.language.entities.mg.runtime.parts.expressions.operator.MgLunaryOperatorExpression.MgReplication;


public class MgResolveLunaryOperatorExpressionTask extends MgResolveUnaryOperatorExpressionTask {
    @Input
    private final MgLogicalLunaryOperatorCallExpression logicalExpression;

    @Output
    private MgLunaryOperatorExpression expression;

    public MgResolveLunaryOperatorExpressionTask(
        CommandContext context,
        MgLogicalLunaryOperatorCallExpression logicalExpression,
        ExpectedParentInput parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected void onResolve() {
        MgExpression rightChild = resolveChild(logicalExpression.getRight(), getExpectedInput());
        int replicationCount = rightChild.getOutputConnectors().count();

        List<MgReplication> replications = new List<>();
        for(int r = 0; r < replicationCount; r++){
            replications.addLast(new MgReplication(new LunaryOperatorExpressionFilter(
                context,
                logicalExpression.getName(),
                getParent(),
                rightChild,
                r
            ).find()));
        }

        expression = new MgLunaryOperatorExpression(replications, rightChild);
    }

    private ExpectedParentInput getExpectedInput(){
        if(getParent() != null){
            // todo - we might be able to locate only some of the operator candidates
            // todo - what shall we do otherwise?
        } else {
            return null;
        }
    }

    @Override
    public MgExpression getExpression() {
        return expression;
    }
}
