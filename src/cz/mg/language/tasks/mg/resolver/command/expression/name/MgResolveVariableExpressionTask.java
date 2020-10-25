package cz.mg.language.tasks.mg.resolver.command.expression.name;

import cz.mg.language.Todo;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
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

    @Override
    protected void onResolve() {
        new Todo();
//        createNode(createFilter().findOptional());
//
//        if(logicalExpression.getExpression() != null){
//            onResolveChild(logicalExpression.getExpression());
//        }
//
//        createNode(createFilter().find());
    }

    @Override
    public MgExpression getExpression() {
        new Todo();
        return null;
    }

//    private VariableExpressionFilter createFilter(){
//        return new NameExpressionFilter(
//            context,
//            logicalExpression.getName(),
//            getParentInputConnectors(),
//            getChildrenOutputConnectors()
//        );
//    }
}
