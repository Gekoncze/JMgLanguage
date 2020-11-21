package cz.mg.language.entities.mg.unresolved.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Value;
import cz.mg.annotations.requirement.Mandatory;


public class MgUnresolvedOperatorExpression extends MgUnresolvedExpression {
    @Mandatory @Value
    private final ReadableText name;

    public MgUnresolvedOperatorExpression(ReadableText name) {
        this.name = name;
    }

    public ReadableText getName() {
        return name;
    }
}
