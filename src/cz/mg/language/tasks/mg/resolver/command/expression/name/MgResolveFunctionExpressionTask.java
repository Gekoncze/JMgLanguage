package cz.mg.language.tasks.mg.resolver.command.expression.name;

import cz.mg.language.Todo;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveFunctionExpressionTask extends MgResolveExpressionTask {
    public MgResolveFunctionExpressionTask(
        CommandContext context,
        MgLogicalNameCallExpression logicalExpression,
        ExpectedParentInput parent
    ) {
        super(context, parent);
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

//    protected void onResolve() {
//        createNode(createFilter().findOptional());
//
//        if(logicalExpression.getExpression() != null){
//            onResolveChild(logicalExpression.getExpression());
//        }
//
//        createNode(createFilter().find());
//    }
//
//    private NameExpressionFilter createFilter(){
//        return new NameExpressionFilter(
//            context,
//            logicalExpression.getName(),
//            getParentInputConnectors(),
//            getChildrenOutputConnectors()
//        );
//    }
}
