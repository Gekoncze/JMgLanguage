package cz.mg.language.entities.mg.runtime.objects;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.clazzes.MgFunction;
import cz.mg.language.entities.mg.runtime.other.instructions.MgInstruction;


public class MgFunctionObject extends MgObject {
    @Link
    private MgInstruction instruction = null;

    public MgFunctionObject(MgFunction function) {
        super(function);
    }

    public MgInstruction getInstruction() {
        return instruction;
    }

    public void setInstruction(MgInstruction instruction) {
        this.instruction = instruction;
    }
}
