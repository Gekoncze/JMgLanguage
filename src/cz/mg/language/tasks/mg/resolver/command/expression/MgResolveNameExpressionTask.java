package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.entities.mg.runtime.roles.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.parts.MgMemberVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgFunctionExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgLocalVariableExpression;
import cz.mg.language.entities.mg.runtime.roles.MgObject;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.FunctionNode;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.LocalVariableNode;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.NameExpressionFilter;


public class MgResolveNameExpressionTask extends MgResolveExpressionTask {
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
        return createNode(createFilter().findOptional());
    }

    @Override
    protected void onResolveChildren() {
        if(logicalExpression.getExpression() != null){
            onResolveChild(logicalExpression.getExpression());
        }
    }

    @Override
    protected Node onResolveLeave() {
        return createNode(createFilter().find());
    }

    private NameExpressionFilter createFilter(){
        return new NameExpressionFilter(
            context,
            logicalExpression.getName(),
            getParentInputInterface(),
            getChildrenOutputInterface()
        );
    }

    private Node createNode(MgObject object){
        if(object == null){
            return null;
        }

        if(object instanceof MgLocalVariable){
            return new LocalVariableNode((MgLocalVariable) object);
        }

        if(object instanceof MgMemberVariable){
            throw new LanguageException("Member variables are not accessible directly from function body.");
        }

        if(object instanceof MgFunction){
            return new FunctionNode((MgFunction) object);
        }

        throw new RuntimeException();
    }

    @Override
    public MgExpression onCreateExpression() {
        if(getNode() instanceof LocalVariableNode){
            return new MgLocalVariableExpression(
                ((LocalVariableNode) getNode()).getVariable()
            );
        }

        if(getNode() instanceof FunctionNode){
            return new MgFunctionExpression(
                ((FunctionNode) getNode()).getFunction(),
                createChildExpression(),
                gatherInput(),
                gatherOutput()
            );
        }

        throw new RuntimeException();
    }

    private MgExpression createChildExpression(){
        if(getChildren().count() < 1) return null;
        return getChildren().getFirst().onCreateExpression();
    }

    private List<MgLocalVariable> gatherInput(){
        List<MgLocalVariable> input = new List<>();
        for(InputConnector in : getInputInterface().getConnectors()){
            input.addLast(in.getConnection().getConnectionVariable());
        }
        return input;
    }

    private List<MgLocalVariable> gatherOutput(){
        List<MgLocalVariable> output = new List<>();
        for(OutputConnector out : getOutputInterface().getConnectors()){
            output.addLast(out.getConnection().getConnectionVariable());
        }
        return output;
    }
}
