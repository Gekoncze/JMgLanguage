package cz.mg.language.entities.mg.logical.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.components.MgLogicalLocation;


public class MgLogicalApplication extends MgLogicalArchitectureEntity {
    @Part
    private final MgLogicalLocation root = new MgLogicalLocation();

    public MgLogicalApplication(ReadableText name) {
        super(name);
    }

    public MgLogicalLocation getRoot() {
        return root;
    }
}
