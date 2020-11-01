package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.tasks.mg.resolver.context.Context;


public abstract class Filter<C extends MgComponent> {
    @Optional @Link
    private final Context context;

    @Optional @Link
    private final ReadableText requiredName;

    @Mandatory @Link
    private final Class<C> requiredType;

    public Filter(Context context, ReadableText requiredName, Class<C> requiredType
    ) {
        this.context = context;
        this.requiredName = requiredName;
        this.requiredType = requiredType;
    }

    public @Optional C find(boolean optional){
        if(optional){
            return findOptional();
        } else {
            return find();
        }
    }

    public @Mandatory C find(){
        ReadableList components = findAll();
        if(components.count() <= 0){
            throw new LanguageException(notFoundMessage());
        } else if(components.count() == 1){
            return (C) components.getFirst();
        } else {
            throw new LanguageException(ambiguousMessage());
        }
    }

    public @Optional C findOptional(){
        ReadableList components = findAll();
        if(components.count() <= 0){
            return null;
        } else if(components.count() == 1){
            return (C) components.getFirst();
        } else {
            return null;
        }
    }

    public @Mandatory ReadableList<C> findAll(){
        List<C> components = new List<>();
        Context context = this.context;
        while(context != null){
            context.forEachComponent((component, alias) -> {
                component = filter(component, alias);
                if(component != null) components.addLast((C) component);
            });
            context = context.getOuterContext();
        }
        return components;
    }

    private @Optional C filter(@Mandatory MgComponent component, @Mandatory ReadableText alias){
        return filter(filterName(filterType(component), alias));
    }

    private @Optional C filterName(@Mandatory C component, @Mandatory ReadableText alias){
        if(component == null){
            return null;
        }

        if(requiredName != null){
            if(!alias.equals(requiredName)){
                return null;
            }
        }

        return component;
    }

    private @Optional C filterType(@Optional MgComponent component){
        if(component == null){
            return null;
        }

        if(!requiredType.isAssignableFrom(component.getClass())){
            return null;
        }

        return (C) component;
    }

    protected abstract @Optional C filter(@Optional C component);

    private String notFoundMessage(){
        return "Component"
            + nameMessage()
            + typeMessage()
            + " was not found.";
    }

    private String ambiguousMessage(){
        return "Component "
            + nameMessage()
            + typeMessage()
            + " is ambiguous.";
    }

    private String nameMessage(){
        if(requiredName != null){
            return " " + requiredName;
        } else {
            return "";
        }
    }

    private String typeMessage(){
        if(requiredType != null){
            return " of type " + requiredType.getSimpleName();
        } else {
            return "";
        }
    }
}
