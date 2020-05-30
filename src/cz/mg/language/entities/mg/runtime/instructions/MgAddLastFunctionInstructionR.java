package cz.mg.language.entities.mg.runtime.instructions;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgFunctionR;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObjectR;
import cz.mg.language.entities.mg.runtime.architecture.MgThreadR;


public class MgAddLastFunctionInstructionR extends MgInstructionR {
    @Link
    private final MgFunctionR function;

    @Link
    private MgInstructionR nextInstruction = null;

    public MgAddLastFunctionInstructionR(MgFunctionR function) {
        this.function = function;
    }

    public MgInstructionR getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstructionR nextInstruction) {
        this.nextInstruction = nextInstruction;
    }

    @Override
    public MgInstructionR run(MgThreadR thread) {
        MgFunctionObjectR functionObject = new MgFunctionObjectR(function);
        thread.getFunctionObjects().addLast(functionObject);
        return nextInstruction;
    }
}
