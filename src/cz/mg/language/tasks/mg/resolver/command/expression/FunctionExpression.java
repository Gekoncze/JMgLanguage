package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;


public class FunctionExpression extends Expression {
    @Link
    private MgLogicalFunction function;

    @Link
    private final List<Expression> arguments = new List<>();

    public FunctionExpression() {
    }

    public MgLogicalFunction getFunction() {
        return function;
    }

    public void setFunction(MgLogicalFunction function) {
        this.function = function;
    }

    public List<Expression> getArguments() {
        return arguments;
    }
}
