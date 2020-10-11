package cz.mg.language.entities.mg.runtime.components.variables.buildin;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;


public class MgExpressionVariable extends MgFunctionVariable {
    public MgExpressionVariable(ReadableText name) {
        super(name);
    }
}
