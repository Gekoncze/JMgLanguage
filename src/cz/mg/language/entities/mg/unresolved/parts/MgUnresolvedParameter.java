package cz.mg.language.entities.mg.unresolved.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;


public class MgUnresolvedParameter extends MgUnresolvedPart implements Named {
    @Value
    private ReadableText name;

    @Link
    private ReadableText type;

    public MgUnresolvedParameter() {
    }

    public MgUnresolvedParameter(ReadableText name, ReadableText type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public void setName(ReadableText name) {
        this.name = name;
    }

    public ReadableText getType() {
        return type;
    }

    public void setType(ReadableText type) {
        this.type = type;
    }
}
