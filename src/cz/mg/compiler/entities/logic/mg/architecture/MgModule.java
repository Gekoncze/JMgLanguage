package cz.mg.compiler.entities.logic.mg.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Link;
import cz.mg.compiler.annotations.entity.Value;


public class MgModule extends MgLocation {
    @Value
    private final List<ReadableText> externalName;

    @Link
    private final List<MgModule> dependencies = new List<>();

    public MgModule(ReadableText name, List<ReadableText> externalName) {
        super(name);
        this.externalName = externalName;
    }

    public List<ReadableText> getExternalName() {
        return externalName;
    }

    public List<MgModule> getDependencies() {
        return dependencies;
    }
}
