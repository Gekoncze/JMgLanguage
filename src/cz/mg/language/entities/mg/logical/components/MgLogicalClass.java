package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalClass extends MgLogicalComponent {
    @Value
    private final List<ReadableText> baseClasses = new List<>();

    @Part
    private final List<MgLogicalVariable> globalVariables = new List<>();

    @Part
    private final List<MgLogicalVariable> variables = new List<>();

    @Part
    private final List<MgLogicalFunction> functions = new List<>();

    public MgLogicalClass() {
    }

    public MgLogicalClass(ReadableText name) {
        super(name);
    }

    public List<ReadableText> getBaseClasses() {
        return baseClasses;
    }

    public List<MgLogicalVariable> getGlobalVariables() {
        return globalVariables;
    }

    public List<MgLogicalVariable> getVariables() {
        return variables;
    }

    public List<MgLogicalFunction> getFunctions() {
        return functions;
    }
}
