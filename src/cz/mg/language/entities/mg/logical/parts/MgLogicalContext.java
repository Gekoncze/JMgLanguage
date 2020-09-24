package cz.mg.language.entities.mg.logical.parts;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Part;


public class MgLogicalContext extends MgLogicalPart {
    @Part
    private final List<MgLogicalUsage> usages = new List<>();

    public MgLogicalContext() {
    }

    public List<MgLogicalUsage> getUsages() {
        return usages;
    }
}
