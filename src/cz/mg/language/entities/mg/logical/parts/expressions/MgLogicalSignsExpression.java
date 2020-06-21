package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalSignsExpression extends MgLogicalExpression {
    @Value
    private ReadableText target;

    public MgLogicalSignsExpression() {
    }

    public MgLogicalSignsExpression(ReadableText target) {
        this.target = target;
    }

    public ReadableText getTarget() {
        return target;
    }

    public void setTarget(ReadableText target) {
        this.target = target;
    }
}
