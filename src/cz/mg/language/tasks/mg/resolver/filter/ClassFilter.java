package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.types.classes.MgClass;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class ClassFilter extends AbstractClassFilter<MgClass> {
    public ClassFilter(Context context, ReadableText name) {
        super(context, name, MgClass.class);
    }
}
