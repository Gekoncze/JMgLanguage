package cz.mg.language.entities.logic.mg.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;


public class MgModule extends MgLocation {
    @Link
    private final List<MgModule> dependencies = new List<>();

    public MgModule(ReadableText name) {
        super(name);
    }

    public List<MgModule> getDependencies() {
        return dependencies;
    }
}
