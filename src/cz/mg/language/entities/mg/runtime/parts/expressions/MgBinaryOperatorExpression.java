package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgBinaryOperatorExpression extends MgOperatorExpression {
    @Mandatory @Part
    private final List<MgExpression> expressions = new List<>();

    @Mandatory @Part
    private final List<Replication> replications = new List<>();

    public MgBinaryOperatorExpression() {
    }

    public MgBinaryOperatorExpression(List<MgExpression> expressions, List<Replication> replications) {
        this.expressions.addCollectionLast(expressions);
        this.replications.addCollectionLast(replications);
    }

    public List<Replication> getReplications() {
        return replications;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        for(MgExpression expression : expressions){
            expression.run(functionObject);
        }

        // Note: It is guaranteed that for every function object
        //       input variables are first in their order and then output variables in their order.
        for(Replication replication : replications){
            // create new function object
            MgFunctionObject newFunctionObject = new MgFunctionObject(replication.getFunction());

            // set input for newly created function object
            newFunctionObject.getObjects().set(functionObject.getObjects().get(replication.getLeftInput()), 0);
            newFunctionObject.getObjects().set(functionObject.getObjects().get(replication.getRightInput()), 1);

            // run the function
            replication.getFunction().run(newFunctionObject);

            // get output of the newly created function object
            functionObject.getObjects().set(newFunctionObject.getObjects().get(2), replication.getOutput());
        }
    }

    public static class Replication {
        @Mandatory @Part
        private final MgFunction function;

        @Mandatory @Part
        private final int leftInput;

        @Mandatory @Part
        private final int rightInput;

        @Mandatory @Part
        private final int output;

        public Replication(
            MgFunction function,
            int leftInput,
            int rightInput,
            int output
        ) {
            this.function = function;
            this.leftInput = leftInput;
            this.rightInput = rightInput;
            this.output = output;
            if(function.getInput().count() != 2) throw new RuntimeException();
            if(function.getOutput().count() != 1) throw new RuntimeException();
        }

        public MgFunction getFunction() {
            return function;
        }

        public int getLeftInput() {
            return leftInput;
        }

        public int getRightInput() {
            return rightInput;
        }

        public int getOutput() {
            return output;
        }
    }
}
