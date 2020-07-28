package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Shared;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


class Command {
    @Shared
    private final CommandContext context;

    @Link
    private final MgLogicalCommand logicalCommand;

    @Shared
    private final List<Command> commands = new List<>();

    @Part
    private final List<Condition> conditions = new List<>();

    @Shared
    private final List<MgInstruction> instructions = new List<>();

    @Shared
    private final List<MgVariable> declaredVariables = new List<>();

    @Shared
    private final List<MgVariable> expressionVariables = new List<>();

    public Command(CommandContext context, MgLogicalCommand logicalCommand) {
        this.context = context;
        this.logicalCommand = logicalCommand;
    }

    public CommandContext getContext() {
        return context;
    }

    public MgLogicalCommand getLogicalCommand() {
        return logicalCommand;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public List<MgInstruction> getInstructions() {
        return instructions;
    }

    public List<MgVariable> getDeclaredVariables() {
        return declaredVariables;
    }

    public List<MgVariable> getExpressionVariables() {
        return expressionVariables;
    }
}