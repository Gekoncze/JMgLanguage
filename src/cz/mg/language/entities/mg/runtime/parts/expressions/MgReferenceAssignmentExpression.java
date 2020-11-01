package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.Pass;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.variable.MgVariableSetExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.DeclarationHelper;


public class MgReferenceAssignmentExpression extends MgExpression {
    @Mandatory @Part
    private final MgExpression leftExpression;

    @Mandatory @Part
    private final MgExpression rightExpression;

    public MgReferenceAssignmentExpression(MgExpression leftExpression, MgExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        connect();
    }

    private void connect(){
        Pass<MgInputConnector> input = getInputConnectors(leftExpression, new List<>()).iterator();
        Pass<MgOutputConnector> output = rightExpression.getOutputConnectors().iterator();

        while(input.hasNext() && output.hasNext()){
            DeclarationHelper.connect(input.next(), output.next());
        }
    }

    private static List<MgInputConnector> getInputConnectors(MgExpression leftExpression, List<MgInputConnector> inputConnectors){
        if(leftExpression instanceof MgGroupExpression){
            for(MgExpression expression : leftExpression.getExpressions()){
                getInputConnectors(expression, inputConnectors);
            }
        } else if(leftExpression instanceof MgVariableSetExpression){
            inputConnectors.addLast(leftExpression.getInputConnectors().iterator().next());
        } else {
            throw new LanguageException("Expected variable set expression for reference assignment, but got " + leftExpression.getClass().getSimpleName() + ".");
        }
        return inputConnectors;
    }

    @Override
    protected ReadableCollection<MgExpression> getExpressions() {
        return new Array<>(leftExpression, rightExpression);
    }

    @Override
    protected ReadableCollection<MgInputConnector> getInputConnectors() {
        return new Array<>();
    }

    @Override
    protected ReadableCollection<MgOutputConnector> getOutputConnectors() {
        return new Array<>();
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        rightExpression.run(functionInstance);
        leftExpression.run(functionInstance);
    }
}
