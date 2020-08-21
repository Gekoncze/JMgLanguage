package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.Context;


public class OperatorFilter extends ClassFilter<MgFunction> {
    public OperatorFilter(Context context) {
        super(context, null, MgFunction.class);
    }

    @Override
    protected boolean filter(MgComponent component) {
        if(super.filter(component)){
            MgFunction function = (MgFunction) component;
            if(function.getOperator() != null){
                return true;
            }
        }
        return false;
    }
}
