package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import java.util.Objects;


public class MgLogicalValueExpression extends MgLogicalExpression {
    @Mandatory @Value
    private final ReadableText value;

    public MgLogicalValueExpression(ReadableText value) {
        Objects.nonNull(value);
        this.value = value;
    }

    public ReadableText getValue() {
        return value;
    }
}
