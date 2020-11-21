package cz.mg.language.entities.mg.runtime.parts.expressions.function;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.Pass;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.utilities.DeclarationHelper;


public abstract class MgFunctionExpression extends MgExpression {
    @Mandatory @Part
    private final List<@Mandatory @Part MgExpression> expressions;

    @Mandatory @Part
    private final List<@Mandatory @Part MgInputConnector> inputConnectors;

    @Mandatory @Part
    private final List<@Mandatory @Part MgOutputConnector> outputConnectors;

    @Mandatory @Link
    private final MgFunction function;

    @Optional @Cache
    private List<@Mandatory @Cache Integer> inputVariableOffsets;

    @Optional @Cache
    private List<@Mandatory @Cache Integer> outputVariableOffsets;

    @Optional @Cache
    private List<@Mandatory @Cache Integer> functionInputVariableOffsets;

    @Optional @Cache
    private List<@Mandatory @Cache Integer> functionOutputVariableOffsets;

    public MgFunctionExpression(MgFunction function, List<MgExpression> expressions) {
        this.function = function;
        this.expressions = expressions;
        this.inputConnectors = createInputConnectors(function);
        this.outputConnectors = createOutputConnectors(function);
        connect();
    }

    private void connect(){
        Pass<MgInputConnector> inputConnectorPass = inputConnectors.iterator();
        for(MgExpression expression : expressions){
            Pass<MgOutputConnector> outputConnectorPass = expression.getOutputConnectors().iterator();
            while(inputConnectorPass.hasNext() && outputConnectorPass.hasNext()){
                DeclarationHelper.connect(inputConnectorPass.next(), outputConnectorPass.next());
            }
        }
    }

    public MgFunction getFunction() {
        return function;
    }

    @Override
    public ReadableList<MgExpression> getExpressions() {
        return expressions;
    }

    @Override
    public ReadableList<MgInputConnector> getInputConnectors() {
        return inputConnectors;
    }

    @Override
    public ReadableList<MgOutputConnector> getOutputConnectors() {
        return outputConnectors;
    }

    private static List<MgInputConnector> createInputConnectors(MgFunction function){
        List<MgInputConnector> connectors = new List<>();
        for(int i = 0; i < connectors.count(); i++){
            connectors.addLast(new MgInputConnector(function.getInputVariables().get(i).getDatatype()));
        }
        return connectors;
    }

    private static List<MgOutputConnector> createOutputConnectors(MgFunction function){
        List<MgOutputConnector> connectors = new List<>();
        for(int i = 0; i < connectors.count(); i++){
            connectors.addLast(new MgOutputConnector(function.getOutputVariables().get(i).getDatatype()));
        }
        return connectors;
    }

    @Override
    public void validate() {
        super.validate();

        inputVariableOffsets = new List<>();
        for(MgInputConnector inputConnector : inputConnectors){
            inputVariableOffsets.addLast(
                inputConnector
                    .getConnection()
                    .getConnectionVariable()
                    .getCache()
                    .getOffset()
            );
        }

        outputVariableOffsets = new List<>();
        for(MgOutputConnector outputConnector : outputConnectors){
            outputVariableOffsets.addLast(
                outputConnector
                    .getConnection()
                    .getConnectionVariable()
                    .getCache()
                    .getOffset()
            );
        }

        functionInputVariableOffsets = new List<>();
        for(MgInstanceVariable variable : function.getInputVariables()){
            functionInputVariableOffsets.addLast(
                variable
                    .getCache()
                    .getOffset()
            );
        }

        functionOutputVariableOffsets = new List<>();
        for(MgInstanceVariable variable : function.getOutputVariables()){
            functionOutputVariableOffsets.addLast(
                variable
                    .getCache()
                    .getOffset()
            );
        }
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        MgFunctionInstance newFunctionInstance = new MgFunctionInstanceImpl(function);
        setFunctionInstanceInput(functionInstance, newFunctionInstance);
        function.run(functionInstance);
        getFunctionInstanceOutput(functionInstance, newFunctionInstance);
    }

    public void setFunctionInstanceInput(
        MgFunctionInstance functionInstance,
        MgFunctionInstance newFunctionInstance
    ){
        Pass<Integer> functionInputVariableOffsetPass = functionInputVariableOffsets.iterator();
        Pass<Integer> inputVariableOffsetPass = inputVariableOffsets.iterator();
        while(functionInputVariableOffsetPass.hasNext() && inputVariableOffsetPass.hasNext()){
            newFunctionInstance.getObjects().set(
                functionInstance.getObjects().get(inputVariableOffsetPass.next()),
                functionInputVariableOffsetPass.next()
            );
        }
    }

    public void getFunctionInstanceOutput(
        MgFunctionInstance functionInstance,
        MgFunctionInstance newFunctionInstance
    ){
        Pass<Integer> functionOutputVariableOffsetPass = functionOutputVariableOffsets.iterator();
        Pass<Integer> outputVariableOffsetPass = outputVariableOffsets.iterator();
        while(functionOutputVariableOffsetPass.hasNext() && outputVariableOffsetPass.hasNext()){
            functionInstance.getObjects().set(
                newFunctionInstance.getObjects().get(functionOutputVariableOffsetPass.next()),
                outputVariableOffsetPass.next()
            );
        }
    }
}
