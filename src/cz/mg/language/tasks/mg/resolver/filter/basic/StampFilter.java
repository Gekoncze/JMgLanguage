package cz.mg.language.tasks.mg.resolver.filter.basic;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class StampFilter extends BasicFilter<MgStamp> {
    public StampFilter(@Optional Context context, @Optional ReadableText requiredName) {
        super(context, requiredName, MgStamp.class);
    }
}
