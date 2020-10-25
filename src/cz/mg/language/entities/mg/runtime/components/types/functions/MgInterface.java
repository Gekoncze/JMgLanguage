package cz.mg.language.entities.mg.runtime.components.types.functions;

import cz.mg.collections.Clump;
import cz.mg.collections.list.ArrayList;
import cz.mg.collections.special.CompositeCollection;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.types.MgStructuredType;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgObjectType;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;


public class MgInterface extends MgStructuredType {
    @Mandatory @Part
    private final ArrayList<MgInstanceVariable> inputVariables = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgInstanceVariable> outputVariables = new ArrayList<>();

    public MgInterface(ReadableText name) {
        super(name);
    }

    public ArrayList<MgInstanceVariable> getInputVariables() {
        return inputVariables;
    }

    public ArrayList<MgInstanceVariable> getOutputVariables() {
        return outputVariables;
    }

    @Override
    public Clump<? extends MgVariable> getVariables() {
        return new CompositeCollection<>(getInputVariables(), getOutputVariables());
    }

    @Override
    public boolean is(MgType baseType) {
        if(baseType == MgObjectType.getInstance()) return true;
        if(baseType == this) return true;
        return false;
    }
}
