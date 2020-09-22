package cz.mg.language.tasks.mg.resolver.command.expression.basic;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalFunctionCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.basic.MgBasicExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.basic.MgFunctionExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.FunctionNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.FunctionExpressionFilter;


public class MgResolveFunctionExpressionTask extends MgResolveMemberAccessibleExpressionTask {
    @Input
    private final MgLogicalFunctionCallExpression logicalExpression;

    public MgResolveFunctionExpressionTask(
        CommandContext context,
        MgLogicalFunctionCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    public FunctionNode getNode() {
        return (FunctionNode) super.getNode();
    }

    @Override
    protected Node onResolveEnter() {
        FunctionExpressionFilter filter = new FunctionExpressionFilter(
            context,
            logicalExpression.getName(),
            getParentInputInterface(),
            null
        );

        MgFunction function = filter.findOptional();
        if(function != null){
            return new FunctionNode(function);
        }

        return null;
    }

    @Override
    protected void onResolveChildren() {
        onResolveChild(logicalExpression.getExpression());
    }

    @Override
    protected Node onResolveLeave() {
        FunctionExpressionFilter filter = new FunctionExpressionFilter(
            context,
            logicalExpression.getName(),
            getParentInputInterface(),
            getChildrenOutputInterface()
        );

        return new FunctionNode(filter.find());
    }

    @Override
    public MgFunctionExpression createExpression(){
        return new MgFunctionExpression(
            getNode().getFunction(),
            createChildExpression(),
            gatherInput(),
            gatherOutput()
        );
    }

    protected MgBasicExpression createChildExpression(){
        if(!getChildren().isEmpty()){
            return asBasicExpression(getChildren().getFirst().createExpression());
        } else {
            return null;
        }
    }

    public List<MgLocalVariable> gatherInput(){
        List<MgLocalVariable> offset = new List<>();
        for(InputConnector in : getInputInterface().getConnectors()){
            offset.addLast(in.getConnection().getConnectionVariable());
        }
        return offset;
    }

    public List<MgLocalVariable> gatherOutput(){
        List<MgLocalVariable> offset = new List<>();
        for(OutputConnector out : getOutputInterface().getConnectors()){
            offset.addLast(out.getConnection().getConnectionVariable());
        }
        return offset;
    }
}
