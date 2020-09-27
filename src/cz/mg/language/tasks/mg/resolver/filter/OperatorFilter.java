package cz.mg.language.tasks.mg.resolver.filter;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.roles.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgFunction;
import cz.mg.language.entities.mg.runtime.roles.MgObject;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class OperatorFilter extends AbstractClassFilter<MgFunction> {
    public OperatorFilter(Context context) {
        super(context, null, MgFunction.class);
    }

    @Override
    protected boolean filter(MgObject object, ReadableText alias) {
        if(super.filter(object, alias)){
            MgFunction function = (MgFunction) object;
            if(function.getOperator() != null){
                return true;
            }
        }
        return false;
    }
}
