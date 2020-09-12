package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.collections.array.Array;
import cz.mg.collections.special.CompositeCollection;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.VariableHelper;


public class FunctionContext extends Context {
    @Optional @Link
    private MgFunction function;

    @Cache
    private OperatorCache operatorCache;

    @Part
    private VariableHelper variableHelper;

    public FunctionContext(Context outerContext) {
        super(outerContext);
    }

    public MgFunction getFunction() {
        return function;
    }

    public void setFunction(MgFunction function) {
        this.function = function;
        this.variableHelper = VariableHelper.create(function);
    }

    public OperatorCache getOperatorCache() {
        if(operatorCache == null) operatorCache = new OperatorCache(this);
        return operatorCache;
    }

    public VariableHelper getVariableHelper() {
        return variableHelper;
    }

    @Override
    public Iterable<? extends MgComponent> read() {
        if(function == null) return new Array<>();
        return new CompositeCollection(function.getInput(), function.getOutput(), function.getLocal());
    }
}
