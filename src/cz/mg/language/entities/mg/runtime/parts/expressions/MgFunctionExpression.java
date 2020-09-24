package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgFunctionExpression extends MgExpression {
    @Mandatory @Part
    private final MgFunction function;

    @Optional @Part
    private final MgExpression expression;

    @Mandatory @Link
    private final List<MgLocalVariable> input;

    @Mandatory @Link
    private final List<MgLocalVariable> output;

    public MgFunctionExpression(
        MgFunction function,
        MgExpression expression,
        List<MgLocalVariable> input,
        List<MgLocalVariable> output
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

    public List<MgLocalVariable> getInput() {
        return input;
    }

    public List<MgLocalVariable> getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        if(expression!= null){
            expression.run(functionObject);
        }

        int local = 0;

        // create new function object
        MgFunctionObject newFunctionObject = new MgFunctionObject(function);

        // set input for newly created function object
        for(MgLocalVariable in : input){
            newFunctionObject.getObjects().set(functionObject.getObjects().get(in.getOffset()), local);
            local++;
        }

        // run the function
        function.run(functionObject);

        // get output of the newly created function object
        for(MgLocalVariable out : output){
            functionObject.getObjects().set(newFunctionObject.getObjects().get(local), out.getOffset());
            local++;
        }
    }
}
