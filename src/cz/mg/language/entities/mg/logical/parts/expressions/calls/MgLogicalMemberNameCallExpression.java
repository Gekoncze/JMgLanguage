package cz.mg.language.entities.mg.logical.parts.expressions.calls;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;


public class MgLogicalMemberNameCallExpression extends MgLogicalCallExpression {
    @Mandatory @Part
    private final MgLogicalCallExpression target;

    @Mandatory @Value
    private final ReadableText name;

    @Optional @Part
    private final MgLogicalCallExpression expression;

    public MgLogicalMemberNameCallExpression(MgLogicalCallExpression target, ReadableText name) {
        this.target = target;
        this.name = name;
        this.expression = null;
    }

    public MgLogicalMemberNameCallExpression(MgLogicalCallExpression target, ReadableText name, MgLogicalCallExpression expression) {
        this.target = target;
        this.name = name;
        this.expression = expression;
    }

    public MgLogicalCallExpression getTarget() {
        return target;
    }

    public ReadableText getName() {
        return name;
    }

    public MgLogicalCallExpression getExpression() {
        return expression;
    }
}
