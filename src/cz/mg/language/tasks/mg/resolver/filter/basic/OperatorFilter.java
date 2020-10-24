package cz.mg.language.tasks.mg.resolver.filter.basic;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class OperatorFilter extends BasicFilter<MgOperator> {
    public OperatorFilter(Context context, ReadableText requiredName) {
        super(context, requiredName, MgOperator.class);
    }
}
