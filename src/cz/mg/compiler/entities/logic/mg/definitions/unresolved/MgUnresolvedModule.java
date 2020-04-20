package cz.mg.compiler.entities.logic.mg.definitions.unresolved;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.entities.logic.mg.architecture.MgModule;


public class MgUnresolvedModule extends MgModule {
    public MgUnresolvedModule(ReadableText name, List<ReadableText> externalName) {
        super(name, externalName);
    }
}
