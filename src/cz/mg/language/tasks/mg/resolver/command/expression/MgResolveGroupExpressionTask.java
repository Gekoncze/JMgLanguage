package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalGroupCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgGroupExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.GroupNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveGroupExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalGroupCallExpression logicalExpression;

    public MgResolveGroupExpressionTask(
        CommandContext context,
        MgLogicalGroupCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected Node onResolveEnter() {
        if(getParentInputInterface() != null){
            return new GroupNode(getParentInputInterface());
        }
        return null;
    }

    @Override
    protected void onResolveChildren() {
        for(MgLogicalCallExpression expression : logicalExpression.getExpressions()){
            onResolveChild(expression);
        }
    }

    @Override
    protected Node onResolveLeave() {
        return new GroupNode(getChildrenOutputInterface());
    }

    @Override
    public cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression onCreateExpression() {
        return new MgGroupExpression(createExpressions());
    }

    private List<MgExpression> createExpressions(){
        List<MgExpression> expressions = new List<>();
        for(MgResolveExpressionTask child : getChildren()){
            expressions.addLast(child.createExpression());
        }
        return expressions;
    }
}
