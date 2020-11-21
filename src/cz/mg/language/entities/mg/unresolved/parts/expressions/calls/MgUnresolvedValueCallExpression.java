package cz.mg.language.entities.mg.unresolved.parts.expressions.calls;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Value;
import cz.mg.annotations.requirement.Mandatory;
import java.util.Objects;


public class MgUnresolvedValueCallExpression extends MgUnresolvedCallExpression {
    @Mandatory @Value
    private final ReadableText value;

    public MgUnresolvedValueCallExpression(ReadableText value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public ReadableText getValue() {
        return value;
    }
}
