package cz.mg.language.entities.runtime.mg.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;


public class MgModuleR extends MgArchitectureEntityR {
    @Value
    private final ReadableText name;

    @Value
    private final MgVersionR version;

    @Part
    private final List<MgModuleDependencyR> dependencies = new List<>();

    @Part
    private final MgRootR root = new MgRootR();

    public MgModuleR(ReadableText name, MgVersionR version) {
        this.name = name;
        this.version = version;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public MgVersionR getVersion() {
        return version;
    }

    public List<MgModuleDependencyR> getDependencies() {
        return dependencies;
    }

    public MgLocationR getRoot() {
        return root;
    }
}
