package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


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
    public void run(MgFunctionInstance functionInstance) {
        expression.run(functionInstance);

        for(Replication replication : replications){
            replication.run(functionInstance);
        }
    }

    public static class Replication implements MgRunnable {
        @Mandatory @Link
        private final MgFunction function;

        @Mandatory @Link
        private final MgFunctionVariable input;

        @Mandatory @Link
        private final MgFunctionVariable output;

        public Replication(
            MgFunction function,
            MgFunctionVariable input,
            MgFunctionVariable output
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

        public MgFunctionVariable getInput() {
            return input;
        }

        public MgFunctionVariable getOutput() {
            return output;
        }

        @Override
        public void run(MgFunctionInstance functionInstance) {
            // create new function object
            MgFunctionInstanceImpl newFunctionObject = new MgFunctionInstanceImpl(function);

            // set input for newly created function object
            newFunctionObject.getObjects().set(functionInstance.getObjects().get(input.getOffset()), 0);

            // run the function
            function.run(newFunctionObject);

            // get output of the newly created function object
            functionInstance.getObjects().set(newFunctionObject.getObjects().get(1), output.getOffset());
        }
    }
}
