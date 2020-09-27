package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;


public abstract class MgUnaryOperatorExpression extends MgOperatorExpression {
    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Part
    private final List<Replication> replications = new List<>();

    public MgUnaryOperatorExpression(MgExpression expression, List<Replication> replications) {
        this.expression = expression;
        this.replications.addCollectionLast(replications);
    }

    public MgExpression getExpression() {
        return expression;
    }

    public List<Replication> getReplications() {
        return replications;
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject) {
        expression.run(functionObject);

        for(Replication replication : replications){
            // create new function object
            MgFunctionInstanceImpl newFunctionObject = new MgFunctionInstanceImpl(replication.getFunction());

            // set input for newly created function object
            newFunctionObject.getObjects().set(functionObject.getObjects().get(replication.getInput().getOffset()), 0);

            // run the function
            replication.getFunction().run(newFunctionObject);

            // get output of the newly created function object
            functionObject.getObjects().set(newFunctionObject.getObjects().get(1), replication.getOutput().getOffset());
        }
    }

    public static class Replication {
        @Mandatory @Link
        private final MgFunction function;

        @Mandatory @Link
        private final MgLocalVariable input;

        @Mandatory @Link
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
