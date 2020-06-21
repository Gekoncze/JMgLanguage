package cz.mg.language.entities.mg.runtime.other.instructions;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.clazzes.MgFunction;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.clazzes.architecture.MgThreadR;


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
    public MgInstruction run(MgThreadR thread) {
        MgFunctionObject functionObject = new MgFunctionObject(function);
        thread.getFunctionObjects().addLast(functionObject);
        return nextInstruction;
    }
}
