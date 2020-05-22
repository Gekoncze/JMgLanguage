package cz.mg.language.entities.logic.mg.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Shared;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.MgEntityL;
import cz.mg.language.entities.logic.mg.MgNamedL;
import cz.mg.language.entities.logic.mg.parts.MgContextL;
import cz.mg.language.entities.logic.mg.parts.MgUsageL;


public class MgComponentL extends MgEntityL implements MgNamedL {
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
