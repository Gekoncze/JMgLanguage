package cz.mg.language.entities.mg.unresolved.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.language.entities.mg.unresolved.MgUnresolvedEntity;
import cz.mg.language.entities.mg.unresolved.Stampable;


public class MgUnresolvedComponent extends MgUnresolvedEntity implements Named, Stampable {
    @Value
    private ReadableText name;

    @Value
    private final List<ReadableText> stamps = new List<>();

    @Shared
    private MgUnresolvedWorkspace workspace;

    public MgUnresolvedComponent() {
    }

    public MgUnresolvedComponent(ReadableText name) {
        this.name = name;
    }

    @Override
    public List<ReadableText> getStamps() {
        return stamps;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public void setName(ReadableText name) {
        this.name = name;
    }

    public MgUnresolvedWorkspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(MgUnresolvedWorkspace workspace) {
        this.workspace = workspace;
    }
}
