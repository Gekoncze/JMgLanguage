package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;


public class FunctionExpression extends Expression {
    @Link
    private MgFunction function;

    @Link
    private final List<Expression> arguments = new List<>();

    public FunctionExpression() {
    }

    public MgFunction getFunction() {
        return function;
    }

    public void setFunction(MgFunction function) {
        this.function = function;
    }

    public List<Expression> getArguments() {
        return arguments;
    }
}
