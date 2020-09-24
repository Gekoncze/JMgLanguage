package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.contexts.utilities.Usage;


public abstract class ComponentContext extends Context {
    @Mandatory @Part
    private final List<Usage> usages = new List<>();

    public ComponentContext(@Optional Context outerContext) {
        super(outerContext);
    }

    public List<Usage> getUsages() {
        return usages;
    }

    @Override
    public void forEachComponent(ComponentVisitor visitor) {
        // todo
    }

    public abstract MgComponent getComponent();
}
