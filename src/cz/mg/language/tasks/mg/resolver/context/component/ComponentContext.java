package cz.mg.language.tasks.mg.resolver.context.component;

import cz.mg.collections.list.List;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.tasks.mg.resolver.command.utilities.Usage;
import cz.mg.language.tasks.mg.resolver.context.Context;


public abstract class ComponentContext extends Context {
    @Mandatory @Part
    private final List<Usage> usages = new List<>();

    public ComponentContext(@Optional Context outerContext) {
        super(outerContext);
    }

    public List<Usage> getUsages() {
        return usages;
    }

    public abstract MgComponent getComponent();
}
