package cz.mg.language.entities.mg.runtime.components.types.functions;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgObjectType;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;


public class MgInterface extends MgType {
    @Mandatory @Part
    private final ArrayList<MgFunctionVariable> input = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgFunctionVariable> output = new ArrayList<>();

    public MgInterface(ReadableText name) {
        super(name);
    }

    public ArrayList<MgFunctionVariable> getInput() {
        return input;
    }

    public ArrayList<MgFunctionVariable> getOutput() {
        return output;
    }

    @Override
    public boolean is(MgType baseType) {
        if(baseType == MgObjectType.getInstance()) return true;
        if(baseType == this) return true;
        return false;
    }
}
