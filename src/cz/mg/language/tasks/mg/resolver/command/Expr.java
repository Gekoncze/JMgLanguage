package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Shared;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;


public class Expr {
    @Shared
    private final List<MgVariable> declaredVariables = new List<>();

    @Shared
    private final List<MgInstruction> instructions = new List<>();

    @Link
    private final List<MgVariable> output = new List<>();

    public Expr() {
    }

    public List<MgVariable> getDeclaredVariables() {
        return declaredVariables;
    }

    public List<MgInstruction> getInstructions() {
        return instructions;
    }

    public List<MgVariable> getOutput() {
        return output;
    }
}
