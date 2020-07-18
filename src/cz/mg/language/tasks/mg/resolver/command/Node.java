package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


class Node {
    private final CommandContext context;
    private final MgLogicalCommand command;
    private final Node next;
    private final List<Condition> conditions = new List<>();
    private final List<MgInstruction> instructions = new List<>();

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
}