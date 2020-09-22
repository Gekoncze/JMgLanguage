package cz.mg.language.tasks.mg.resolver.command.expression.basic;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgMemberVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.basic.MgFunctionExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.basic.MgLocalVariableExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.FunctionNode;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.LocalVariableNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.NameExpressionFilter;


public class MgResolveNameExpressionTask extends MgResolveMemberAccessibleExpressionTask {
    @Input
    private final MgLogicalNameCallExpression logicalExpression;

    public MgResolveNameExpressionTask(
        CommandContext context,
        MgLogicalNameCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected Node onResolveEnter() {
        return null;
    }

    @Override
    protected void onResolveChildren() {
    }

    @Override
    protected Node onResolveLeave() {
        NameExpressionFilter filter = new NameExpressionFilter(
            context,
            logicalExpression.getName(),
            getParentInputInterface(),
            getChildrenOutputInterface()
        );
        MgComponent component = filter.find();
        if(component instanceof MgLocalVariable){
            return new LocalVariableNode((MgLocalVariable) component);
        } else if(component instanceof MgMemberVariable){
            throw new LanguageException("Member variables are not accessible directly from function body.");
        } else if(component instanceof MgFunction){
            return new FunctionNode((MgFunction) component);
        } {
            throw new RuntimeException();
        }
    }

    @Override
    public MgExpression createExpression() {
        if(getNode() instanceof LocalVariableNode){
            return new MgLocalVariableExpression(
                ((LocalVariableNode) getNode()).getVariable()
            );
        }

        if(getNode() instanceof FunctionNode){
            return new MgFunctionExpression(
                ((FunctionNode) getNode()).getFunction(),
                null,
                new List<>(),
                gatherOutput()
            );
        }

        throw new RuntimeException();
    }

    public List<MgLocalVariable> gatherOutput(){
        List<MgLocalVariable> offset = new List<>();
        for(OutputConnector out : getOutputInterface().getConnectors()){
            offset.addLast(out.getConnection().getConnectionVariable());
        }
        return offset;
    }
}
