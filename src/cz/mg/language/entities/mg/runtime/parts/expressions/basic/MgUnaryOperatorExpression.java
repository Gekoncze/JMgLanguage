package cz.mg.language.entities.mg.runtime.parts.expressions.basic;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public abstract class MgUnaryOperatorExpression extends MgOperatorExpression {
    @Mandatory @Part
    private final MgBasicExpression expression;

    @Mandatory @Part
    private final List<Replication> replications = new List<>();

    public MgUnaryOperatorExpression(MgBasicExpression expression, List<Replication> replications) {
        this.expression = expression;
        this.replications.addCollectionLast(replications);
    }

    public MgBasicExpression getExpression() {
        return expression;
    }

    public List<Replication> getReplications() {
        return replications;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        expression.run(functionObject);

        for(Replication replication : replications){
            // create new function object
            MgFunctionObject newFunctionObject = new MgFunctionObject(replication.getFunction());

            // set input for newly created function object
            newFunctionObject.getObjects().set(functionObject.getObjects().get(replication.getInput().getOffset()), 0);

            // run the function
            replication.getFunction().run(newFunctionObject);

            // get output of the newly created function object
            functionObject.getObjects().set(newFunctionObject.getObjects().get(1), replication.getOutput().getOffset());
        }
    }

    public static class Replication {
        @Mandatory @Part
        private final MgFunction function;

        @Mandatory @Part
        private final MgLocalVariable input;

        @Mandatory @Part
        private final MgLocalVariable output;

        public Replication(
            MgFunction function,
            MgLocalVariable input,
            MgLocalVariable output
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

        public MgLocalVariable getInput() {
            return input;
        }

        public MgLocalVariable getOutput() {
            return output;
        }
    }
}
