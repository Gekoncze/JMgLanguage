package cz.mg.language.entities.mg.logical.parts.expressions.calls;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import java.util.Objects;


public class MgLogicalNameCallExpression extends MgLogicalCallExpression {
    @Mandatory @Value
    private final ReadableText name;

    public MgLogicalNameCallExpression(ReadableText name) {
        Objects.nonNull(name);
        this.name = name;
    }

    public ReadableText getName() {
        return name;
    }
}
