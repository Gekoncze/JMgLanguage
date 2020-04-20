package cz.mg.compiler.entities.logic.mg.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.mg.definitions.MgDefinition;
import cz.mg.compiler.entities.logic.mg.definitions.unresolved.MgUnresolvedDefinition;


public class MgNameCall extends MgExpression {
    @Value
    private final MgDefinition definition;

    public MgNameCall(ReadableText name) {
        this.definition = new MgUnresolvedDefinition(name);
    }

    public MgDefinition getDefinition() {
        return definition;
    }
}
