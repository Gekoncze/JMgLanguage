package cz.mg.language.entities.mg.runtime.parts.expressions.function;

import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgGlobalFunctionExpression extends MgFunctionExpression {
    public MgGlobalFunctionExpression(MgFunction function, List<MgExpression> expressions) {
        super(function, expressions);
    }
}
