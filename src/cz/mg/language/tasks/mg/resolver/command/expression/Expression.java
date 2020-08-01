package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Shared;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class Expression {
    @Shared
    private final CommandContext context;

    @Link
    private final MgLogicalExpression logicalExpression;

    @Part
    private final List<Expression> expressions = new List<>();

    @Part
    private final List<MgInstruction> instructions = new List<>();

    @Part
    private final List<MgVariable> declaredVariables = new List<>();

    public Expression(CommandContext context, MgLogicalExpression logicalExpression) {
        this.context = context;
        this.logicalExpression = logicalExpression;
    }

    public CommandContext getContext() {
        return context;
    }

    public MgLogicalExpression getLogicalExpression() {
        return logicalExpression;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public List<MgInstruction> getInstructions() {
        return instructions;
    }

    public List<MgVariable> getDeclaredVariables() {
        return declaredVariables;
    }
}
