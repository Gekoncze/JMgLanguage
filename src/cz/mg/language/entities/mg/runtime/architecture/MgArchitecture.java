package cz.mg.language.entities.mg.runtime.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.roles.MgObject;


public abstract class MgArchitecture implements Named, MgObject {
    @Value
    private final ReadableText name;

    public MgArchitecture(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
