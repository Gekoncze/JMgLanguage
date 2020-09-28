package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;


public class MgLogicalOperatorExpression extends MgLogicalExpression {
    @Mandatory @Value
    private final ReadableText name;

    @Optional @Cache
    private MgOperator operator;

    public MgLogicalOperatorExpression(ReadableText name) {
        this.name = name;
    }

    public MgLogicalOperatorExpression(ReadableText name, MgOperator operator) {
        this.name = name;
        this.operator = operator;
    }

    public ReadableText getName() {
        return name;
    }

    public MgOperator getOperator() {
        return operator;
    }

    public void setOperator(MgOperator operator) {
        this.operator = operator;
    }
}
