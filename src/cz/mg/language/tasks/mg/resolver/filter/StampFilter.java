package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class StampFilter extends AbstractClassFilter<MgStamp> {
    public StampFilter(Context context, ReadableText name) {
        super(context, name, MgStamp.class);
    }
}
