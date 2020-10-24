package cz.mg.language.tasks.mg.resolver.command.expression.name;

import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.expression.variable.VariableExpressionFilter;


public class MgResolveVariableExpressionTask extends MgResolveNameExpressionTask {
    public MgResolveVariableExpressionTask(
        CommandContext context,
        MgLogicalNameCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, logicalExpression, parent);
    }

    protected void onResolve() {
        createNode(createFilter().findOptional());

        if(logicalExpression.getExpression() != null){
            onResolveChild(logicalExpression.getExpression());
        }

        createNode(createFilter().find());
    }

    private VariableExpressionFilter createFilter(){
        return new NameExpressionFilter(
            context,
            logicalExpression.getName(),
            getParentInputConnectors(),
            getChildrenOutputConnectors()
        );
    }
}
