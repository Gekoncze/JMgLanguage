package cz.mg.language.entities.mg.runtime.components.types.buildin.operators;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.MgOperatorInfo;


public abstract class MgBuildinOperator extends MgOperator {
    public static final List<MgBuildinOperator> ALL = new List<>();

    public MgBuildinOperator(ReadableText name, MgOperatorInfo info) {
        super(name, info);
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject) {
        buildinRun(functionObject);
    }

    protected abstract void buildinRun(MgFunctionInstanceImpl functionObject);
}
