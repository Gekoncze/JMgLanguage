package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;


public class MgFunctionExpression extends MgExpression {
    @Mandatory @Part
    private final MgFunction function;

    @Optional @Part
    private final MgExpression expression;

    @Mandatory @Link
    private final List<MgFunctionVariable> input;

    @Mandatory @Link
    private final List<MgFunctionVariable> output;

    public MgFunctionExpression(
        MgFunction function,
        MgExpression expression,
        List<MgFunctionVariable> input,
        List<MgFunctionVariable> output
    ) {
        this.function = function;
        this.expression = expression;
        this.input = input;
        this.output = output;
    }

    public MgFunction getFunction() {
        return function;
    }

    public cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression getExpression() {
        return expression;
    }

    public List<MgFunctionVariable> getInput() {
        return input;
    }

    public List<MgFunctionVariable> getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject) {
        if(expression!= null){
            expression.run(functionObject);
        }

        int local = 0;

        // create new function object
        MgFunctionInstanceImpl newFunctionObject = new MgFunctionInstanceImpl(function);

        // set input for newly created function object
        for(MgFunctionVariable in : input){
            newFunctionObject.getObjects().set(functionObject.getObjects().get(in.getOffset()), local);
            local++;
        }

        // run the function
        function.run(functionObject);

        // get output of the newly created function object
        for(MgFunctionVariable out : output){
            functionObject.getObjects().set(newFunctionObject.getObjects().get(local), out.getOffset());
            local++;
        }
    }
}
