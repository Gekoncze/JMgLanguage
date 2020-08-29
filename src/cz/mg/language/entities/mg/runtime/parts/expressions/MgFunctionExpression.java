package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgFunctionExpression extends MgMemberAccessibleExpression {
    @Mandatory @Part
    private final MgFunction function;

    @Mandatory @Part
    private final List<MgExpression> expressions;

    @Mandatory @Part
    private final List<Integer> input;

    @Mandatory @Part
    private final List<Integer> output;

    public MgFunctionExpression(MgFunction function, List<MgExpression> expressions, List<Integer> input, List<Integer> output) {
        this.function = function;
        this.expressions = expressions;
        this.input = input;
        this.output = output;
    }

    public MgFunction getFunction() {
        return function;
    }

    public List<MgExpression> getExpressions() {
        return expressions;
    }

    public List<Integer> getInput() {
        return input;
    }

    public List<Integer> getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        for(MgExpression expression : expressions){
            expression.run(functionObject);
        }

        int local = 0;

        // create new function object
        MgFunctionObject newFunctionObject = new MgFunctionObject(function);

        // set input for newly created function object
        for(Integer i : input){
            newFunctionObject.getObjects().set(functionObject.getObjects().get(i), local);
            local++;
        }

        // run the function
        function.run(functionObject);

        // get output of the newly created function object
        for(Integer o : output){
            functionObject.getObjects().set(newFunctionObject.getObjects().get(local), o);
            local++;
        }
    }

    @Override
    public void run(MgFunctionObject functionObject, MgClassObject classObject) {
        for(MgExpression expression : expressions){
            expression.run(functionObject);
        }

        int local = 0;

        // create new function object
        MgFunctionObject newFunctionObject = new MgFunctionObject(function);

        // set parent object as first input argument for member access function call
        newFunctionObject.getObjects().set(classObject, local);
        local++;

        // set input for newly created function object
        for(Integer i : input){
            newFunctionObject.getObjects().set(functionObject.getObjects().get(i), local);
            local++;
        }

        // run the function
        function.run(functionObject);

        // get output of the newly created function object
        for(Integer o : output){
            functionObject.getObjects().set(newFunctionObject.getObjects().get(local), o);
            local++;
        }
    }
}
