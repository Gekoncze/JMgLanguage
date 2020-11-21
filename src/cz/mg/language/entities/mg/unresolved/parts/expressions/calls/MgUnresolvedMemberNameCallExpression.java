package cz.mg.language.entities.mg.unresolved.parts.expressions.calls;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;


public class MgUnresolvedMemberNameCallExpression extends MgUnresolvedCallExpression {
    @Mandatory @Part
    private final MgUnresolvedCallExpression target;

    @Mandatory @Value
    private final ReadableText name;

    @Optional @Part
    private final MgUnresolvedCallExpression expression;

    public MgUnresolvedMemberNameCallExpression(MgUnresolvedCallExpression target, ReadableText name) {
        this.target = target;
        this.name = name;
        this.expression = null;
    }

    public MgUnresolvedMemberNameCallExpression(MgUnresolvedCallExpression target, ReadableText name, MgUnresolvedCallExpression expression) {
        this.target = target;
        this.name = name;
        this.expression = expression;
    }

    public MgUnresolvedCallExpression getTarget() {
        return target;
    }

    public ReadableText getName() {
        return name;
    }

    public MgUnresolvedCallExpression getExpression() {
        return expression;
    }
}
