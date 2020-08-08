package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public class Operator {
    @Value
    private final Type type;

    @Value
    private final int priority;

    @Link
    private final MgLogicalExpression expression;

    @Part
    private Operator left;

    @Part
    private Operator right;

    public Operator(Type type, int priority, MgLogicalExpression expression) {
        this.type = type;
        this.priority = priority;
        this.expression = expression;
    }

    public Type getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public MgLogicalExpression getExpression() {
        return expression;
    }

    public Operator getLeft() {
        return left;
    }

    public void setLeft(Operator left) {
        this.left = left;
    }

    public Operator getRight() {
        return right;
    }

    public void setRight(Operator right) {
        this.right = right;
    }

    public enum Type {
        BINARY,
        LUNARY,
        RUNARY,
        LEAF
    }
}
