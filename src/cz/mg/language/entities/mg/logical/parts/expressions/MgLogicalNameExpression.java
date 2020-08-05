package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalNameExpression extends MgLogicalExpression {
    @Value
    private ReadableText name;

    public MgLogicalNameExpression() {
    }

    public MgLogicalNameExpression(ReadableText name) {
        this.name = name;
    }

    public ReadableText getName() {
        return name;
    }

    public void setName(ReadableText name) {
        this.name = name;
    }
}
