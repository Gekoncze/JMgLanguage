package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgUnaryOperatorExpression extends MgOperatorExpression {
    @Mandatory @Part
    private final List<MgExpression> expressions = new List<>();

    @Mandatory @Part
    private final List<Replication> replications = new List<>();

    public MgUnaryOperatorExpression() {
    }

    public MgUnaryOperatorExpression(List<MgExpression> expressions, List<Replication> replications) {
        this.expressions.addCollectionLast(expressions);
        this.replications.addCollectionLast(replications);
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        for(MgExpression expression : expressions){
            expression.run(functionObject);
        }

        for(Replication replication : replications){
            // create new function object
            MgFunctionObject newFunctionObject = new MgFunctionObject(replication.getFunction());

            // set input for newly created function object
            newFunctionObject.getObjects().set(functionObject.getObjects().get(replication.getInput()), 0);

            // run the function
            replication.getFunction().run(newFunctionObject);

            // get output of the newly created function object
            functionObject.getObjects().set(newFunctionObject.getObjects().get(1), replication.getOutput());
        }
    }

    public static class Replication {
        @Mandatory @Part
        private final MgFunction function;

        @Mandatory @Part
        private final int input;

        @Mandatory @Part
        private final int output;

        public Replication(
            MgFunction function,
            int input,
            int output
        ) {
            this.function = function;
            this.input = input;
            this.output = output;
            if(function.getInput().count() != 1) throw new RuntimeException();
            if(function.getOutput().count() != 1) throw new RuntimeException();
        }

        public MgFunction getFunction() {
            return function;
        }

        public int getInput() {
            return input;
        }

        public int getOutput() {
            return output;
        }
    }
}
