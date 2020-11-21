package cz.mg.language.entities.mg.unresolved.components;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.text.ReadableText;


public class MgUnresolvedAlias extends MgUnresolvedComponent {
    @Optional @Part
    private ReadableText original;

    public MgUnresolvedAlias(ReadableText name) {
        super(name);
    }

    public ReadableText getOriginal() {
        return original;
    }

    public void setOriginal(ReadableText original) {
        this.original = original;
    }
}
