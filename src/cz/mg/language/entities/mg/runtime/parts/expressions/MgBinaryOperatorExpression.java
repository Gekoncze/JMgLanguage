package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgBinaryOperatorExpression extends MgOperatorExpression {
    @Mandatory @Part
    private final MgFunction function;

    @Mandatory @Part
    private final List<MgExpression> leftExpressions;

    @Mandatory @Part
    private final List<MgExpression> rightExpressions;

    @Mandatory @Part
    private final Array<Integer> leftInput;

    @Mandatory @Part
    private final Array<Integer> rightInput;

    @Mandatory @Part
    private final Array<Integer> output;

    public MgBinaryOperatorExpression(MgFunction function, List<MgExpression> leftExpressions, List<MgExpression> rightExpressions, Array<Integer> leftInput, Array<Integer> rightInput, Array<Integer> output) {
        if(leftExpressions.count() != rightExpressions.count()) throw new IllegalArgumentException();
        this.function = function;
        this.leftExpressions = leftExpressions;
        this.rightExpressions = rightExpressions;
        this.leftInput = leftInput;
        this.rightInput = rightInput;
        this.output = output;
    }

    public List<MgExpression> getLeftExpressions() {
        return leftExpressions;
    }

    public List<MgExpression> getRightExpressions() {
        return rightExpressions;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        for(MgExpression operand : leftExpressions){
            operand.run(functionObject);
        }

        for(MgExpression operand : rightExpressions){
            operand.run(functionObject);
        }

        for(int i = 0; i < output.count(); i++){
            // create new function object
            MgFunctionObject newFunctionObject = new MgFunctionObject(function);

            // set input for newly created function object
            newFunctionObject.getObjects().set(functionObject.getObjects().get(leftInput.get(i)), 0);
            newFunctionObject.getObjects().set(functionObject.getObjects().get(rightInput.get(i)), 1);

            // run the function
            function.run(newFunctionObject);

            // get output of the newly created function object
            functionObject.getObjects().set(newFunctionObject.getObjects().get(2), output.get(i));
        }
    }
}
