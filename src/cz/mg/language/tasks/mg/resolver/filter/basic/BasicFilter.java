package cz.mg.language.tasks.mg.resolver.filter.basic;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.filter.Filter;


public class BasicFilter<C extends MgComponent> extends Filter<C> {
    public BasicFilter(Context context, ReadableText requiredName, Class<C> requiredType) {
        super(context, requiredName, requiredType);
    }

    @Override
    protected final C filter(@Optional C component) {
        return component;
    }
}
