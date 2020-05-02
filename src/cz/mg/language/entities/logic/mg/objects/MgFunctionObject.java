package cz.mg.language.entities.logic.mg.objects;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.logic.mg.definitions.MgFunction;
import cz.mg.language.entities.logic.mg.instructions.MgInstruction;


public class MgFunctionObject extends MgObject {
    @Link
    private final MgFunction function;

    @Link
    private MgInstruction instruction = null;

    @Link
    private final Array<MgObject> objects;

    public MgFunctionObject(MgFunction function) {
        this.function = function;
        this.objects = new Array<>(
                function.getInput().count() +
                function.getOutput().count() +
                function.getVariables().count()
        );
    }

    public MgFunction getFunction() {
        return function;
    }

    public MgInstruction getInstruction() {
        return instruction;
    }

    public void setInstruction(MgInstruction instruction) {
        this.instruction = instruction;
    }

    public Array<MgObject> getObjects() {
        return objects;
    }
}
