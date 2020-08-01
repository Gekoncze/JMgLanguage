package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.tasks.mg.resolver.Context;


public class CommandContext extends Context {
    @Link
    private final List<MgVariable> variables = new List<>();

    public CommandContext(CommandContext outerContext) {
        super(outerContext);
    }

    public CommandContext(FunctionContext outerContext) {
        super(outerContext);
    }

    public OperatorCache getOperatorCache() {
        Context context = getOuterContext();
        while(context != null){
            if(context instanceof FunctionContext){
                return ((FunctionContext) context).getOperatorCache();
            } else {
                context = context.getOuterContext();
            }
        }
        throw new RuntimeException("Command context must be inside of function context.");
    }

    @Override
    public Iterable<? extends MgComponent> read() {
        return variables;
    }
}
