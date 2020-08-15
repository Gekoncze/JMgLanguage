package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.tasks.mg.resolver.Context;


public abstract class AbstractFilter<C extends MgComponent> {
    @Optional @Link
    private final Context context;

    public AbstractFilter(Context context) {
        this.context = context;
    }

    public C find(){
        ReadableList<C> components = findAll();
        if(components.count() <= 0){
            throw new LanguageException(notFoundMessage());
        } else if(components.count() == 1){
            return components.getFirst();
        } else {
            throw new LanguageException(ambiguousMessage());
        }
    }

    public C findOptional(){
        ReadableList<C> components = findAll();
        if(components.count() <= 0){
            return null;
        } else if(components.count() == 1){
            return components.getFirst();
        } else {
            return null;
        }
    }

    public ReadableList<C> findAll(){
        List<C> components = new List<>();
        Context currentContext = context;
        while(currentContext != null){
            for(MgComponent component : currentContext.read()){
                if(filter(component)){
                    components.addLast((C) component);
                }
            }
            currentContext = currentContext.getOuterContext();
        }
        return components;
    }

    protected abstract boolean filter(MgComponent component);

    protected abstract String notFoundMessage();

    protected abstract String ambiguousMessage();
}
