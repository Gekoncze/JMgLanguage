package cz.mg.language.tasks.mg.resolvers;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.logical.components.MgLogicalLocation;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolvers.contexts.FunctionContext;


public class MgResolveFunctionTask extends MgResolverTask {
    @Input
    private final MgLogicalLocation logicalLocation;

    @Input
    private final MgLogicalFunction logicalFunction;

    @Input
    private final MgLocation location;

    @Input
    private final MgFunction function;

    @Part
    private final Context context;

    public MgResolveFunctionTask(MgLogicalLocation logicalLocation, MgLogicalFunction logicalFunction, MgLocation location, MgFunction function, Context outerContext) {
        this.logicalLocation = logicalLocation;
        this.logicalFunction = logicalFunction;
        this.location = location;
        this.function = function;
        this.context = new FunctionContext(outerContext, function);
    }

    @Override
    protected void onRun() {
        function.setStamps(resolveStamps());
        function.setInput(resolveInput());
        function.setOutput(resolveOutput());
        function.setLocal(resolveLocal());
        function.setInstructions(resolveInstructions());
        function.setOperator(resolveOperator());
    }
}
