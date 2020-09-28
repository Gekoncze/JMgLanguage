package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;


public class LocalVariableNode extends VariableNode {
    @Mandatory @Link
    private final MgFunctionVariable variable;

    public LocalVariableNode(MgFunctionVariable variable) {
        super(variable);
        this.variable = variable;
    }

    public MgFunctionVariable getVariable() {
        return variable;
    }
}
