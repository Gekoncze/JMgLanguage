package cz.mg.language.entities.mg.runtime.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.MgRuntimeEntity;


public class MgModuleDependencyR extends MgRuntimeEntity {
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
