package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalMemberAccessCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.entities.mg.runtime.roles.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgClass;
import cz.mg.language.entities.mg.runtime.components.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.parts.MgMemberVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgMemberFunctionExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgMemberVariableExpression;
import cz.mg.language.entities.mg.runtime.roles.MgObject;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.MemberFunctionNode;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.MemberVariableNode;
import cz.mg.language.tasks.mg.resolver.context.ClassContext;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.NameExpressionFilter;


public class MgResolveMemberAccessExpression extends MgResolveExpressionTask {
    @Input
    private final MgLogicalNameCallExpression logicalExpression;

    @Input
    private final MgLogicalCallExpression logicalTargetChildExpression;

    public MgResolveMemberAccessExpression(
        CommandContext context,
        MgLogicalMemberAccessCallExpression logicalExpression,
        MgResolveExpressionTask parent
    ) {
        super(context, parent);
        this.logicalExpression = logicalExpression.getRight();
        this.logicalTargetChildExpression = logicalExpression.getLeft();
    }

    @Override
    protected Node onResolveEnter() {
        return null;
    }

    @Override
    protected void onResolveChildren() {
        resolveTargetChild();
        verifyResolutionOfTargetChild();
        resolveMemberAccessOptional();
        resolveRegularChild();
        resolveMemberAccessMandatory();
    }

    private void resolveTargetChild(){
        onResolveChild(logicalTargetChildExpression);
    }

    private void verifyResolutionOfTargetChild(){
        if(getChildren().getFirst().getOutputInterface().getConnectors().count() != 1){
            throw new LanguageException("Member access requires single output.");
        }
    }

    private void resolveMemberAccessOptional(){
        setNode(createNode(createFilter().findOptional()));
    }

    private void resolveRegularChild(){
        if(logicalExpression.getExpression() != null){
            onResolveChild(logicalExpression.getExpression());
        }
    }

    private void resolveMemberAccessMandatory(){
        setNode(createNode(createFilter().find()));
    }

    private Node createNode(MgObject object){
        if(object == null){
            return null;
        }

        if(object instanceof MgMemberVariable){
            return new MemberVariableNode((MgMemberVariable) object);
        }

        if(object instanceof MgFunction){
            return new MemberFunctionNode((MgFunction) object);
        }

        throw new RuntimeException();
    }

    @Override
    protected Node onResolveLeave() {
        return null;
    }

    private NameExpressionFilter createFilter(){
        return new NameExpressionFilter(
            createTargetContext(),
            logicalExpression.getName(),
            getParentInputInterface(),
            getChildOutputInterface(),
            getTargetDatatype()
        );
    }

    private ClassContext createTargetContext(){
        ClassContext targetContext = new ClassContext(null);
        MgDatatype targetDatatype = getTargetDatatype();
        if(targetDatatype.getType() instanceof MgClass){
            targetContext.setClazz((MgClass) targetDatatype.getType());
        } else {
            throw new LanguageException("Target of member access must be of class type.");
        }
        return targetContext;
    }

    private OutputInterface getChildOutputInterface(){
        return getChildren().getLast().getOutputInterface();
    }

    private MgDatatype getTargetDatatype(){
        return getChildren()
            .getFirst()
            .getOutputInterface()
            .getConnectors()
            .getFirst()
            .getRequestedDatatype();
    }

    @Override
    public MgExpression onCreateExpression() {
        if(getNode() instanceof MemberVariableNode){
            return new MgMemberVariableExpression(
                createTargetChildExpression(),
                ((MemberVariableNode) getNode()).getVariable(),
                gatherVariableInput(),
                gatherVariableOutput()
            );
        }

        if(getNode() instanceof MemberFunctionNode){
            return new MgMemberFunctionExpression(
                createTargetChildExpression(),
                ((MemberFunctionNode) getNode()).getFunction(),
                createRegularChildExpression(),
                gatherFunctionInput(),
                gatherFunctionOutput()
            );
        }

        throw new RuntimeException();
    }

    private MgLocalVariable gatherVariableInput(){
        return getChildren()
            .getFirst()
            .getOutputInterface()
            .getConnectors()
            .getFirst()
            .getConnection()
            .getConnectionVariable();
    }

    private List<MgLocalVariable> gatherFunctionInput(){
        List<MgLocalVariable> input = new List<>();
        input.addLast(gatherVariableInput());
        for(InputConnector in : getInputInterface().getConnectors()){
            input.addLast(in.getConnection().getConnectionVariable());
        }
        return input;
    }

    private MgLocalVariable gatherVariableOutput(){
        return getOutputInterface()
            .getConnectors()
            .getFirst()
            .getConnection()
            .getConnectionVariable();
    }

    private List<MgLocalVariable> gatherFunctionOutput(){
        List<MgLocalVariable> output = new List<>();
        for(OutputConnector out : getOutputInterface().getConnectors()){
            output.addLast(out.getConnection().getConnectionVariable());
        }
        return output;
    }

    private MgExpression createTargetChildExpression(){
        return getChildren().getFirst().createExpression();
    }

    private MgExpression createRegularChildExpression(){
        if(getChildren().count() < 2) return null;
        return getChildren().getLast().createExpression();
    }
}
