package cz.mg.language.entities.logic.c.parts.expressions.calls;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.c.parts.expressions.CExpression;


public class CFunctionCall extends CCall {
    @Value
    private final ReadableText name;

    @Part
    private final List<CExpression> input = new List<>();

    public CFunctionCall(ReadableText name, CExpression... input) {
        this.name = name;
        this.input.addCollectionLast(input);
    }

    public ReadableText getName() {
        return name;
    }

    public List<CExpression> getInput() {
        return input;
    }
}
