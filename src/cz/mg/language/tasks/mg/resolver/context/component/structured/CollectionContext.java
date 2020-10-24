package cz.mg.language.tasks.mg.resolver.context.component.structured;

import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.classes.MgCollection;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.MgParameter;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class CollectionContext extends StructuredTypeContext {
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
            for(MgVariable variable : collection.getVariables()){
                visitor.onVisitComponent(variable, null);
            }

            for(MgFunction function : collection.getFunctions()){
                visitor.onVisitComponent(function, null);
            }

            for(MgParameter parameter : collection.getParameters()){
                visitor.onVisitComponent(parameter.getType(), parameter.getName());
            }
        }
    }
}
