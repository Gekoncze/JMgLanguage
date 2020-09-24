package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.array.Array;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.tasks.mg.resolver.Context;


public abstract class AbstractClassFilter<C extends MgComponent> extends Filter<C> {
    @Value
    private final ReadableText name;

    @Link
    private final Class<? extends C>[] classes;

    public AbstractClassFilter(Context context, ReadableText name, Class<? extends C>... classes) {
        super(context);
        this.name = name;
        this.classes = classes;
    }

    @Override
    protected boolean filter(MgComponent component) {
        for(Class<? extends C> clazz : classes){
            if(clazz.isInstance(component)){
                if(name == null){
                    return true;
                } else if(name.equals(component)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected String notFoundMessage() {
        ReadableText requiredTypes = new Array<>(classes).toText(" or ", clazz -> new ReadonlyText(clazz.getSimpleName()));
        return "Could not find component " + name + " of type " + requiredTypes + ".";
    }

    @Override
    protected String ambiguousMessage() {
        ReadableText requiredTypes = new Array<>(classes).toText(" or ", clazz -> new ReadonlyText(clazz.getSimpleName()));
        return "Component " + name + " of type " + requiredTypes + " is ambiguous.";
    }
}
