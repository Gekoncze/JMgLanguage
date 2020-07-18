package cz.mg.language.tasks.mg.resolver;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.components.MgComponent;


public class Filter<C extends MgComponent> {
    @Part
    private final Context context;

    @Link
    private final Class<C> clazz;

    @Value
    private final ReadableText name;

    public Filter(Context context, Class<C> clazz, ReadableText name) {
        this.context = context;
        this.clazz = clazz;
        this.name = name;
    }

    public C find(){
        List<C> components = findAll();
        if(components.count() <= 0){
            throw new LanguageException("Could not find " + clazz.getSimpleName() + " " + name + ".");
        } else if(components.count() == 1){
            return components.getFirst();
        } else {
            throw new LanguageException(clazz.getSimpleName() + " " + name + " is ambiguous.");
        }
    }

    public List<C> findAll(){
        List<C> components = new List<>();
        Context currentContext = context;
        while(currentContext != null){
            for(MgComponent component : currentContext.read()){
                if(clazz.isInstance(component)){
                    if(name.equals(component)){
                        components.addLast((C) component);
                    }
                }
            }
            currentContext = currentContext.getOuterContext();
        }
        return components;
    }
}
