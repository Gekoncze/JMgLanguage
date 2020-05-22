package cz.mg.language.entities.logic.mg.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.mg.parts.MgUsageL;


public class MgClassL extends MgComponentL {
    @Link
    private final List<MgUsageL> baseClasses = new List<>();

    @Part
    private final List<MgVariableL> globalVariables = new List<>();

    @Part
    private final List<MgVariableL> variables = new List<>();

    @Part
    private final List<MgFunctionL> functions = new List<>();

    public MgClassL(ReadableText name) {
        super(name);
    }

    public List<MgUsageL> getBaseClasses() {
        return baseClasses;
    }

    public List<MgVariableL> getGlobalVariables() {
        return globalVariables;
    }

    public List<MgVariableL> getVariables() {
        return variables;
    }

    public List<MgFunctionL> getFunctions() {
        return functions;
    }
}
