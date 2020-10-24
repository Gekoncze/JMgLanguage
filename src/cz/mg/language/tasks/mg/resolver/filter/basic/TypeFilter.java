package cz.mg.language.tasks.mg.resolver.filter.basic;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class TypeFilter extends BasicFilter<MgType> {
    public TypeFilter(@Optional Context context, @Optional ReadableText requiredName) {
        super(context, requiredName, MgType.class);
    }
}
