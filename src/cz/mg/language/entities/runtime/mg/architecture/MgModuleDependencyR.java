package cz.mg.language.entities.runtime.mg.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.runtime.mg.MgEntityR;


public class MgModuleDependencyR extends MgEntityR {
    @Link
    private final MgModuleR module;

    @Value
    private final List<ReadableText> alias;

    public MgModuleDependencyR(MgModuleR module, List<ReadableText> alias) {
        this.module = module;
        this.alias = alias;
    }

    public MgModuleR getModule() {
        return module;
    }

    public List<ReadableText> getAlias() {
        return alias;
    }
}
