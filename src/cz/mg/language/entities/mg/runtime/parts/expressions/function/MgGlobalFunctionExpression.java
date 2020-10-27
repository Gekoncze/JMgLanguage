package cz.mg.language.entities.mg.runtime.parts.expressions.function;

import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;


public class MgGlobalFunctionExpression extends MgFunctionExpression {
    public MgGlobalFunctionExpression(MgFunction function) {
        super(
            createInputConnectors(function),
            createOutputConnectors(function),
            function
        );
    }
}
