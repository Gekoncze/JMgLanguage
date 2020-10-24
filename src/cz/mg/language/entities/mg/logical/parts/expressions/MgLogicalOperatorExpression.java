package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Value;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.parts.MgOperatorInfo;


public class MgLogicalOperatorExpression extends MgLogicalExpression {
    @Mandatory @Value
    private final ReadableText name;

    @Optional @Cache
    private MgOperatorInfo operator;

    public MgLogicalOperatorExpression(ReadableText name) {
        this.name = name;
    }

    public MgLogicalOperatorExpression(ReadableText name, MgOperatorInfo operator) {
        this.name = name;
        this.operator = operator;
    }

    public ReadableText getName() {
        return name;
    }

    public MgOperatorInfo getOperator() {
        return operator;
    }

    public void setOperator(MgOperatorInfo operator) {
        this.operator = operator;
    }
}
