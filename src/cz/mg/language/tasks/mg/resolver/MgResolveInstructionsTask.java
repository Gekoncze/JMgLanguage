package cz.mg.language.tasks.mg.resolver;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;


public class MgResolveInstructionsTask extends MgResolverTask {
    @Input
    private final Context context;

    @Input
    private final MgLogicalFunction logicalFunction;

    @Input
    private final MgFunction function;

    public MgResolveInstructionsTask(Context context, MgLogicalFunction logicalFunction, MgFunction function) {
        this.context = context;
        this.logicalFunction = logicalFunction;
        this.function = function;
    }

    @Override
    protected void onRun() {
        List<MgInstruction> instructions = new List<>();
        todo;
        function.setInstructions(new Array<>(instructions));
    }
}
