package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;


public class ReferenceAssignmentNode extends AssignmentNode {
    public ReferenceAssignmentNode(@Mandatory List<? extends MgVariable> variables) {
        super(variables);
    }
}
