package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import java.util.Objects;


public class MgLogicalOperatorExpression extends MgLogicalLessAbstractExpression {
    @Mandatory @Value
    private final ReadableText signs;

    public MgLogicalOperatorExpression(ReadableText signs) {
        Objects.nonNull(signs);
        this.signs = signs;
    }

    public ReadableText getSigns() {
        return signs;
    }
}
