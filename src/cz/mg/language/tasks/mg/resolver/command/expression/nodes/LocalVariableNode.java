package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;


public class LocalVariableNode extends VariableNode {
    @Mandatory @Link
    private final MgLocalVariable variable;

    public LocalVariableNode(MgLocalVariable variable) {
        super(variable);
        this.variable = variable;
    }

    public MgLocalVariable getVariable() {
        return variable;
    }
}
