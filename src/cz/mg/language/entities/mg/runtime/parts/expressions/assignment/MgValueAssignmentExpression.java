package cz.mg.language.entities.mg.runtime.parts.expressions.assignment;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public abstract class MgValueAssignmentExpression extends MgAssignmentExpression {
    public MgValueAssignmentExpression(MgExpression sourceExpression, List<Replication> replications) {
        super(sourceExpression, replications);
    }

    public static class Replication extends MgAssignmentExpression.Replication {
        @Mandatory @Part
        protected final MgExpression destination;

        @Mandatory @Link
        private final MgFunction function;

        @Mandatory @Link
        private final MgFunctionVariable leftInput;

        @Mandatory @Link
        private final MgFunctionVariable rightInput;

        public Replication(
            MgExpression destination,
            MgFunction function,
            MgFunctionVariable leftInput,
            MgFunctionVariable rightInput
        ) {
            this.destination = destination;
            this.function = function;
            this.leftInput = leftInput;
            this.rightInput = rightInput;
        }

        public MgExpression getDestination() {
            return destination;
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

        @Override
        public void run(MgFunctionInstance functionInstance) {
            destination.run(functionInstance);

            // Note: It is guaranteed that for every function object
            //       input variables are first in their order and then output variables in their order.

            // create new function object
            MgFunctionInstanceImpl newFunctionObject = new MgFunctionInstanceImpl(function);

            // set input for newly created function object
            newFunctionObject.getObjects().set(functionInstance.getObjects().get(leftInput.getOffset()), 0);
            newFunctionObject.getObjects().set(functionInstance.getObjects().get(rightInput.getOffset()), 1);

            // run the function
            function.run(newFunctionObject);
        }
    }
}
