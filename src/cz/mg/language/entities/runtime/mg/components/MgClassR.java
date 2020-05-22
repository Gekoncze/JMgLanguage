package cz.mg.language.entities.runtime.mg.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;


public class MgClassR extends MgComponentR {
    @Value
    private final ReadableText name;

    @Link
    private final List<MgClassR> baseClasses = new List<>();

    @Part
    private final List<MgVariableR> variables = new List<>();

    @Part
    private final List<MgFunctionR> functions = new List<>();

    public MgClassR(ReadableText name) {
        this.name = name;
    }

    public List<MgClassR> getBaseClasses() {
        return baseClasses;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public List<MgVariableR> getVariables() {
        return variables;
    }

    public List<MgFunctionR> getFunctions() {
        return functions;
    }
}
