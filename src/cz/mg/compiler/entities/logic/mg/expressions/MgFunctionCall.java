package cz.mg.compiler.entities.logic.mg.expressions;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.mg.definitions.MgFunction;
import cz.mg.compiler.entities.logic.mg.definitions.unresolved.MgUnresolvedFunction;


public class MgFunctionCall extends MgExpression {
    @Value
    private final MgFunction function;

    @Part
    private final List<MgExpression> arguments = new List<>();

    public MgFunctionCall(ReadableText name) {
        this.function = new MgUnresolvedFunction(name);
    }

    public List<MgExpression> getArguments() {
        return arguments;
    }
}
