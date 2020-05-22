package cz.mg.language.entities.runtime.mg.objects;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.runtime.mg.components.MgFunctionR;
import cz.mg.language.entities.runtime.mg.instructions.MgInstructionR;


public class MgFunctionObjectR extends MgObjectR {
    @Link
    private final MgFunctionR function;

    @Link
    private MgInstructionR instruction = null;

    @Link
    private final Array<MgObjectR> objects;

    public MgFunctionObjectR(MgFunctionR function) {
        this.function = function;
        this.objects = new Array<>(
                function.getInput().count() +
                function.getOutput().count() +
                function.getVariables().count()
        );
    }

    public MgFunctionR getFunction() {
        return function;
    }

    public MgInstructionR getInstruction() {
        return instruction;
    }

    public void setInstruction(MgInstructionR instruction) {
        this.instruction = instruction;
    }

    public Array<MgObjectR> getObjects() {
        return objects;
    }
}
