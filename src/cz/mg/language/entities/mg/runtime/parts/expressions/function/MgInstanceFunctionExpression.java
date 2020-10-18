package cz.mg.language.entities.mg.runtime.parts.expressions.function;

import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgInstanceFunctionExpression extends MgFunctionExpression {
    public MgInstanceFunctionExpression(MgFunction function, MgExpression expression) {
        super(function, expression);
    }
}
