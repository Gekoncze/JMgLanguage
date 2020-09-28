package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;


public class MgBinaryOperatorExpression extends MgOperatorExpression {
    @Mandatory @Part
    private final MgExpression leftExpression;

    @Mandatory @Part
    private final MgExpression rightExpression;

    @Mandatory @Part
    private final List<Replication> replications = new List<>();

    public MgBinaryOperatorExpression(
        MgExpression leftExpression,
        MgExpression rightExpression,
        List<Replication> replications
    ) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.replications.addCollectionLast(replications);
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject) {
        leftExpression.run(functionObject);
        rightExpression.run(functionObject);

        // Note: It is guaranteed that for every function object
        //       input variables are first in their order and then output variables in their order.
        for(Replication replication : replications){
            // create new function object
            MgFunctionInstanceImpl newFunctionObject = new MgFunctionInstanceImpl(replication.getFunction());

            // set input for newly created function object
            newFunctionObject.getObjects().set(functionObject.getObjects().get(replication.getLeftInput().getOffset()), 0);
            newFunctionObject.getObjects().set(functionObject.getObjects().get(replication.getRightInput().getOffset()), 1);

            // run the function
            replication.getFunction().run(newFunctionObject);

            // get output of the newly created function object
            functionObject.getObjects().set(newFunctionObject.getObjects().get(2), replication.getOutput().getOffset());
        }
    }

    public static class Replication {
        @Mandatory @Link
        private final MgFunction function;

        @Mandatory @Link
        private final MgFunctionVariable leftInput;

        @Mandatory @Link
        private final MgFunctionVariable rightInput;

        @Mandatory @Link
        private final MgFunctionVariable output;

        public Replication(
            MgFunction function,
            MgFunctionVariable leftInput,
            MgFunctionVariable rightInput,
            MgFunctionVariable output
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

        public MgFunctionVariable getLeftInput() {
            return leftInput;
        }

        public MgFunctionVariable getRightInput() {
            return rightInput;
        }

        public MgFunctionVariable getOutput() {
            return output;
        }
    }
}
