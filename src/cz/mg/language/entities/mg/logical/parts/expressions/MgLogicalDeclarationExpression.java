package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalDeclarationExpression extends MgLogicalExpression {
    @Value
    private ReadableText name;

    @Value
    private ReadableText typename;

    public MgLogicalDeclarationExpression() {
    }

    public MgLogicalDeclarationExpression(ReadableText name, ReadableText typename) {
        this.name = name;
        this.typename = typename;
    }

    public ReadableText getName() {
        return name;
    }

    public void setName(ReadableText name) {
        this.name = name;
    }

    public ReadableText getTypename() {
        return typename;
    }

    public void setTypename(ReadableText typename) {
        this.typename = typename;
    }
}
