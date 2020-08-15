package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;


public class MgFunction extends MgInterface {
    private static final MgType TYPE = new MgType("Function");

    @Mandatory @Part
    private final ArrayList<MgVariable> local = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgGlobalVariable> globalVariables = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgInstruction> instructions = new ArrayList<>();

    @Optional @Part
    private MgOperator operator;

    protected MgFunction(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgFunction(ReadableText name) {
        super(TYPE, name);
    }

    public ArrayList<MgVariable> getLocal() {
        return local;
    }

    public ArrayList<MgGlobalVariable> getGlobalVariables() {
        return globalVariables;
    }

    public ArrayList<MgInstruction> getInstructions() {
        return instructions;
    }

    public MgOperator getOperator() {
        return operator;
    }

    public void setOperator(MgOperator operator) {
        this.operator = operator;
    }
}
