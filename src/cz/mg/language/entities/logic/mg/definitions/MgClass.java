package cz.mg.language.entities.logic.mg.definitions;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.MgLocable;


public class MgClass extends MgDefinition implements MgLocable {
    @Value
    private final ReadableText name;

    @Value
    private final List<MgClass> baseClasses = new List<>();

    @Part
    private final List<MgVariable> variables = new List<>();

    @Part
    private final List<MgFunction> functions = new List<>();

    public MgClass(ReadableText name) {
        this.name = name;
    }

    public List<MgClass> getBaseClasses() {
        return baseClasses;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public List<MgVariable> getVariables() {
        return variables;
    }

    public List<MgFunction> getFunctions() {
        return functions;
    }
}
