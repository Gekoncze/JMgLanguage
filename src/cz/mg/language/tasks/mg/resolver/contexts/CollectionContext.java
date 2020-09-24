package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgCollection;
import cz.mg.language.entities.mg.runtime.parts.MgParameter;
import cz.mg.language.tasks.mg.resolver.Context;


public class CollectionContext extends ComponentContext {
    @Optional @Link
    private MgCollection collection;

    public CollectionContext(Context outerContext) {
        super(outerContext);
    }

    @Override
    public MgComponent getComponent() {
        return collection;
    }

    public MgCollection getCollection() {
        return collection;
    }

    public void setCollection(MgCollection collection) {
        this.collection = collection;
    }

    @Override
    public void forEachComponent(ComponentVisitor visitor) {
        if(collection != null){
            for(MgComponent variable : collection.getVariables()){
                visitor.onVisitComponent(variable);
            }

            for(MgComponent function : collection.getFunctions()){
                visitor.onVisitComponent(function);
            }

            for(MgParameter parameter : collection.getParameters()){
                //visitor.onVisitComponent(parameter); // todo
            }
        }
    }
}
