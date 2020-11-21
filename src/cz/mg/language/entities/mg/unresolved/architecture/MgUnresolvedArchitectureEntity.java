package cz.mg.language.entities.mg.unresolved.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.annotations.storage.Value;
import cz.mg.language.entities.mg.unresolved.MgUnresolvedEntity;


public abstract class MgUnresolvedArchitectureEntity extends MgUnresolvedEntity implements Named {
    @Value
    private ReadableText name;

    public MgUnresolvedArchitectureEntity() {
    }

    public MgUnresolvedArchitectureEntity(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public void setName(ReadableText name) {
        this.name = name;
    }
}
