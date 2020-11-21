package cz.mg.language.entities.mg.unresolved.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.unresolved.components.MgUnresolvedLocation;


public class MgUnresolvedApplication extends MgUnresolvedArchitectureEntity {
    @Part
    private final MgUnresolvedLocation root = new MgUnresolvedLocation(new ReadonlyText(""));

    public MgUnresolvedApplication(ReadableText name) {
        super(name);
    }

    public MgUnresolvedLocation getRoot() {
        return root;
    }
}
