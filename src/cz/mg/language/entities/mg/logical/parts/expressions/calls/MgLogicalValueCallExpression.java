package cz.mg.language.entities.mg.logical.parts.expressions.calls;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import java.util.Objects;


public class MgLogicalValueCallExpression extends MgLogicalCallExpression {
    @Mandatory @Value
    private final ReadableText value;

    public MgLogicalValueCallExpression(ReadableText value) {
        Objects.nonNull(value);
        this.value = value;
    }

    public ReadableText getValue() {
        return value;
    }
}
