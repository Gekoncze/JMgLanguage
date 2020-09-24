package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.tasks.mg.resolver.Context;


public class TypeFilter extends AbstractClassFilter<MgType> {
    public TypeFilter(Context context, ReadableText name) {
        super(context, name, MgType.class);
    }
}
