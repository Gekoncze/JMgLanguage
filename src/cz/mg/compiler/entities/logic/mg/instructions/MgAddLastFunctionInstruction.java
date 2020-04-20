package cz.mg.compiler.entities.logic.mg.instructions;

import cz.mg.compiler.annotations.entity.Link;
import cz.mg.compiler.entities.logic.mg.definitions.MgFunction;
import cz.mg.compiler.entities.logic.mg.objects.MgFunctionObject;
import cz.mg.compiler.entities.logic.mg.architecture.MgThread;


public class MgAddLastFunctionInstruction extends MgInstruction {
    @Link
    private final MgFunction function;

    @Link
    private MgInstruction nextInstruction = null;

    public MgAddLastFunctionInstruction(MgFunction function) {
        this.function = function;
    }

    public MgInstruction getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstruction nextInstruction) {
        this.nextInstruction = nextInstruction;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgFunctionObject functionObject = new MgFunctionObject(function);
        thread.getFunctionObjects().addLast(functionObject);
        return nextInstruction;
    }
}
