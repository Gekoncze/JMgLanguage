package cz.mg.language.tasks.mg.resolver.filter.basic;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.types.classes.MgClass;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class ClassFilter extends BasicFilter<MgClass> {
    public ClassFilter(@Optional Context context, @Optional ReadableText requiredName) {
        super(context, requiredName, MgClass.class);
    }
}
