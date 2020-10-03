package cz.mg.language.entities.mg.runtime.components.types.buildin.operators;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgOperator;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.MgOperatorInfo;


public abstract class MgBuildinOperator extends MgOperator {
    public static final List<MgBuildinOperator> ALL = new List<>();

    public MgBuildinOperator(@Mandatory ReadableText name, @Mandatory MgOperatorInfo operator) {
        super(name, operator);
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject) {
        buildinRun(functionObject);
    }

    protected abstract void buildinRun(MgFunctionInstanceImpl functionObject);
}
