package cz.mg.language.entities.mg.runtime.parts.expressions.assignment;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.variable.MgVariableExpression;


public class MgReferenceAssignmentExpression extends MgAssignmentExpression {
    public MgReferenceAssignmentExpression(MgExpression sourceExpression, List<Replication> replications) {
        super(sourceExpression, replications);
    }

    public static class Replication extends MgAssignmentExpression.Replication {
        @Mandatory @Part
        protected final MgVariableExpression destination;

        @Mandatory @Link
        private final MgFunctionVariable input;

        public Replication(MgVariableExpression destination, MgFunctionVariable input) {
            this.destination = destination;
            this.input = input;
        }

        public MgVariableExpression getDestination() {
            return destination;
        }

        public MgFunctionVariable getInput() {
            return input;
        }

        @Override
        public void run(MgFunctionInstance functionInstance) {
            destination.run(functionInstance);
            destination.getParent(functionInstance).getObjects().set(
                functionInstance.getObjects().get(input.getOffset()),
                destination.getVariable().getOffset()
            );
        }
    }
}
