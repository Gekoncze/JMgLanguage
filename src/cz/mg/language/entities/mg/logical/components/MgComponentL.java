package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Shared;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.MgLogicalEntity;
import cz.mg.language.entities.mg.logical.parts.MgContextL;
import cz.mg.language.entities.mg.logical.parts.MgUsageL;


public class MgComponentL extends MgLogicalEntity implements Named {
    @Link
    private final List<MgUsageL> stamps = new List<>();

    @Value
    private final ReadableText name;

    @Shared
    private final MgContextL context = new MgContextL();

    public MgComponentL(ReadableText name) {
        this.name = name;
    }

    public List<MgUsageL> getStamps() {
        return stamps;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public MgContextL getContext() {
        return context;
    }
}
