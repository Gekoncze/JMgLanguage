package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalVariable extends MgLogicalComponent {
    @Value
    private ReadableText type;

    public MgLogicalVariable() {
    }

    public MgLogicalVariable(ReadableText name, ReadableText type) {
        super(name);
        this.type = type;
    }

    public ReadableText getType() {
        return type;
    }

    public void setType(ReadableText type) {
        this.type = type;
    }
}
