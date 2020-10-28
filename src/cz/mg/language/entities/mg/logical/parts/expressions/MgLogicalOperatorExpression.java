package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Value;
import cz.mg.annotations.requirement.Mandatory;


public class MgLogicalOperatorExpression extends MgLogicalExpression {
    @Mandatory @Value
    private final ReadableText name;

    public MgLogicalOperatorExpression(ReadableText name) {
        this.name = name;
    }

    public ReadableText getName() {
        return name;
    }
}
