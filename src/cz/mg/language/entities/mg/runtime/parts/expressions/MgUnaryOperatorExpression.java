package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgUnaryOperatorExpression extends MgOperatorExpression {
    @Mandatory @Part
    private final MgFunction function;

    @Mandatory @Part
    private final List<MgExpression> expressions;

    @Mandatory @Part
    private final Array<Integer> input;

    @Mandatory @Part
    private final Array<Integer> output;

    public MgUnaryOperatorExpression(
        MgFunction function,
        List<MgExpression> expressions,
        Array<Integer> input,
        Array<Integer> output
    ) {
        if(input.count() != output.count()) throw new IllegalArgumentException();
        this.function = function;
        this.expressions = expressions;
        this.input = input;
        this.output = output;
    }

    public List<MgExpression> getExpressions() {
        return expressions;
    }

    public Array<Integer> getInput() {
        return input;
    }

    public Array<Integer> getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        for(MgExpression operand : expressions){
            operand.run(functionObject);
        }

        for(int i = 0; i < input.count(); i++){
            // create new function object
            MgFunctionObject newFunctionObject = new MgFunctionObject(function);

            // set input for newly created function object
            newFunctionObject.getObjects().set(functionObject.getObjects().get(input.get(i)), 0);

            // run the function
            function.run(newFunctionObject);

            // get output of the newly created function object
            functionObject.getObjects().set(newFunctionObject.getObjects().get(1), output.get(i));
        }
    }
}
