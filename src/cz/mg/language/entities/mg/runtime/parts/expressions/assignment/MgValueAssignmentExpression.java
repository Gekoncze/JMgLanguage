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


public class MgValueAssignmentExpression extends MgAssignmentExpression {
    @Mandatory @Part
    private final MgExpression destinationExpression;

    public MgValueAssignmentExpression(
        MgExpression sourceExpression,
        MgExpression destinationExpression,
        List<Replication> replications
    ) {
        super(sourceExpression, replications);
        this.destinationExpression = destinationExpression;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        destinationExpression.run(functionInstance);
        super.run(functionInstance);
    }

    public static class Replication extends MgAssignmentExpression.Replication {
        @Mandatory @Link
        private final MgFunction function;

        @Mandatory @Link
        private final MgFunctionVariable leftInput;

        @Mandatory @Link
        private final MgFunctionVariable rightInput;

        public Replication(
            MgFunction function,
            MgFunctionVariable leftInput,
            MgFunctionVariable rightInput
        ) {
            this.function = function;
            this.leftInput = leftInput;
            this.rightInput = rightInput;
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
