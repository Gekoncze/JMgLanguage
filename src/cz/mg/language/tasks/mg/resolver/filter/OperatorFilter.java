package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.language.entities.mg.runtime.components.types.MgOperator;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class OperatorFilter extends AbstractClassFilter<MgOperator> {
    public OperatorFilter(Context context) {
        super(context, null, MgOperator.class);
    }
}
