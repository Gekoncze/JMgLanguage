package cz.mg.language.tasks.mg.resolver.command.expression.name;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgClassVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.function.MgFunctionExpression;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.tasks.mg.resolver.command.expression.MgResolveExpressionTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public abstract class MgResolveNameExpressionTask extends MgResolveExpressionTask {
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
    protected void onResolve() {
        createNode(createFilter().findOptional());

        if(logicalExpression.getExpression() != null){
            onResolveChild(logicalExpression.getExpression());
        }

        createNode(createFilter().find());
    }

    private NameExpressionFilter createFilter(){
        return new NameExpressionFilter(
            context,
            logicalExpression.getName(),
            getParentInputConnectors(),
            getChildrenOutputConnectors()
        );
    }

    private Node createNode(MgObject object){
        if(object == null){
            return null;
        }

        if(object instanceof MgFunctionVariable){
            return new LocalVariableNode((MgFunctionVariable) object);
        }

        if(object instanceof MgClassVariable){
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

    private List<MgFunctionVariable> gatherInput(){
        List<MgFunctionVariable> input = new List<>();
        for(InputConnector in : getInputConnectors().getConnectors()){
            input.addLast(in.getConnection().getConnectionVariable());
        }
        return input;
    }

    private List<MgFunctionVariable> gatherOutput(){
        List<MgFunctionVariable> output = new List<>();
        for(OutputConnector out : getOutputConnectors().getConnectors()){
            output.addLast(out.getConnection().getConnectionVariable());
        }
        return output;
    }

    public static MgResolveNameExpressionTask create(
        CommandContext context,
        MgLogicalNameCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ){
        if(logicalExpression.getExpression() == null){
            return new MgResolveVariableExpressionTask(context, logicalExpression, parent);
        } else {
            return new MgResolveFunctionExpressionTask(context, logicalExpression, parent);
        }
    }
}
