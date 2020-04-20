package cz.mg.compiler.entities.logic.c.parts;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.entity.Part;


public class CFunctionType extends CPart {
    @Part
    private final List<CVariable> input = new List<>();

    @Part
    private CType output = CType.VOID;

    public CFunctionType() {
    }

    public List<CVariable> getInput() {
        return input;
    }

    public CType getOutput() {
        return output;
    }

    public void setOutput(CType output) {
        this.output = output;
    }
}
