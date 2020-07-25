package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Shared;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


class Node {
    @Shared
    private final CommandContext context;

    @Link
    private final MgLogicalCommand command;

    @Shared
    private final Node next;

    @Part
    private final List<Condition> conditions = new List<>();

    @Shared
    private final List<MgInstruction> instructions = new List<>();

    @Shared
    private final List<MgVariable> declaredVariables = new List<>();

    @Shared
    private final List<MgVariable> expressionVariables = new List<>();

    public Node(CommandContext context, MgLogicalCommand command, Node next) {
        this.context = context;
        this.command = command;
        this.next = next;
    }

    public CommandContext getContext() {
        return context;
    }

    public MgLogicalCommand getCommand() {
        return command;
    }

    public Node getNext() {
        return next;
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