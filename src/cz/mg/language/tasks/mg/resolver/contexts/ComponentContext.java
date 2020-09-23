package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.tasks.mg.resolver.Context;


public class ComponentContext extends Context {
    @Mandatory @Link
    private List<MgComponent> components;

    public ComponentContext(@Optional Context outerContext) {
        super(outerContext);
    }

    public List<MgComponent> getComponents() {
        return components;
    }

    public void setComponents(List<MgComponent> components) {
        this.components = components;
    }

    @Override
    public void forEachComponent(ComponentVisitor visitor) {
        for(MgComponent component : components){
            visitor.onVisitComponent(component);
        }
    }
}
