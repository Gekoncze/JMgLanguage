package cz.mg.language.entities.mg.runtime.components.types.buildin.operators;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;


public abstract class MgBuildinOperator extends MgFunction {
    public static final List<MgBuildinOperator> ALL = new List<>();

    public MgBuildinOperator(@Mandatory ReadableText name, @Mandatory MgOperator operator) {
        super(name, operator);
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject) {
        buildinRun(functionObject);
    }

    protected abstract void buildinRun(MgFunctionInstanceImpl functionObject);
}
