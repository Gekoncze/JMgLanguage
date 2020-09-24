package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.storage.Shared;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.logical.MgLogicalEntity;
import cz.mg.language.entities.mg.logical.Stampable;
import cz.mg.language.entities.mg.logical.parts.MgLogicalContext;


public class MgLogicalComponent extends MgLogicalEntity implements Named, Stampable {
    @Value
    private ReadableText name;

    @Value
    private final List<ReadableText> stamps = new List<>();

    @Shared
    private MgLogicalContext context;

    public MgLogicalComponent() {
    }

    public MgLogicalComponent(ReadableText name) {
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

    public MgLogicalContext getContext() {
        return context;
    }

    public void setContext(MgLogicalContext context) {
        this.context = context;
    }
}
