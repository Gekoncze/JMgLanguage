package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public class MgLogicalWhileCommand extends MgLogicalBlockCommand {
    @Value
    private ReadableText name;

    @Part
    private MgLogicalExpression expression;

    public MgLogicalWhileCommand() {
    }

    public MgLogicalWhileCommand(ReadableText name, MgLogicalExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    public ReadableText getName() {
        return name;
    }

    public void setName(ReadableText name) {
        this.name = name;
    }

    public MgLogicalExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalExpression expression) {
        this.expression = expression;
    }
}
