package cz.mg.language.entities.mg.runtime.parts.expressions.function;

import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;


public class MgInstanceFunctionExpression extends MgFunctionExpression {
    public MgInstanceFunctionExpression(MgFunction function) {
        super(
            createInputConnectors(function),
            createOutputConnectors(function),
            function
        );
    }
}
