package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.Context;


public class CommandContext extends Context {
    @Link
    private final List<MgVariable> variables = new List<>();

    public CommandContext(CommandContext outerContext) {
        super(notNull(outerContext));
    }

    public CommandContext(FunctionContext outerContext) {
        super(notNull(outerContext));
    }

    public OperatorCache getOperatorCache() {
        if(getOuterContext() instanceof CommandContext){
            return ((CommandContext) getOuterContext()).getOperatorCache();
        } else if(getOuterContext() instanceof FunctionContext) {
            return ((FunctionContext) getOuterContext()).getOperatorCache();
        } else {
            throw new RuntimeException();
        }
    }

    public MgFunction getFunction(){
        if(getOuterContext() instanceof CommandContext){
            return ((CommandContext) getOuterContext()).getFunction();
        } else if(getOuterContext() instanceof FunctionContext) {
            return ((FunctionContext) getOuterContext()).getFunction();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Iterable<? extends MgComponent> read() {
        return variables;
    }

    private static Context notNull(Context context){
        if(context == null) throw new IllegalArgumentException();
        else return context;
    }
}
