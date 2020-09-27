package cz.mg.language.entities.mg.runtime.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.MgLocation;


public class MgApplication extends MgArchitecture {
    @Part
    private final MgLocation root = new MgLocation(new ReadonlyText(""));

    public MgApplication(ReadableText name) {
        super(name);
    }

    public MgLocation getRoot() {
        return root;
    }
}
