package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.tasks.mg.resolver.Context;


public class CommandContext extends Context {
    @Link
    private final List<MgVariable> variables = new List<>();

    @Cache
    private OperatorCache operatorCache;

    public CommandContext(Context outerContext) {
        super(outerContext);
    }

    public OperatorCache getOperatorCache() {
        if(operatorCache == null){
            if(getOuterContext() instanceof CommandContext){
                return operatorCache = ((CommandContext) getOuterContext()).getOperatorCache();
            } else {
                return operatorCache = new OperatorCache(this);
            }
        }
        return operatorCache;
    }

    @Override
    public Iterable<? extends MgComponent> read() {
        return variables;
    }
}
