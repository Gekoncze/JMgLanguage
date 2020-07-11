package cz.mg.language.entities.mg.runtime.objects;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;


public class MgFunctionObject extends MgObject {
    @Link
    private MgInstruction instruction = null;

    @Part
    private final Array<MgObject> objects;

    public MgFunctionObject(MgFunction function) {
        super(function);
        this.objects = generateArray(function);
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

    private static Array<MgObject> generateArray(MgFunction function){
        return new Array<>(
            function.getInput().count() +
            function.getOutput().count() +
            function.getLocal().count()
        );
    }
}
