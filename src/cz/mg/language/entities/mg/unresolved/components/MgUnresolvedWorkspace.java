package cz.mg.language.entities.mg.unresolved.components;

import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.unresolved.parts.MgUnresolvedUsage;


public class MgUnresolvedWorkspace extends MgUnresolvedComponent {
    @Part
    private final List<MgUnresolvedUsage> usages = new List<>();

    public MgUnresolvedWorkspace() {
    }

    public List<MgUnresolvedUsage> getUsages() {
        return usages;
    }
}
