package cz.mg.language.tasks.mg.resolver.context;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.classes.MgCollection;
import cz.mg.language.entities.mg.runtime.parts.MgParameter;
import cz.mg.language.entities.mg.runtime.MgObject;


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
    public void forEachComponent(ObjectVisitor visitor) {
        if(collection != null){
            for(MgObject variable : collection.getVariables()){
                visitor.onVisitComponent(variable, null);
            }

            for(MgObject function : collection.getFunctions()){
                visitor.onVisitComponent(function, null);
            }

            for(MgParameter parameter : collection.getParameters()){
                visitor.onVisitComponent(parameter.getType(), parameter.getName());
            }
        }
    }
}
