package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.parts.expressions.chains.MgLogicalListExpression;


public class MgLogicalParametrizedExpression extends MgLogicalExpression {
    @Value
    private MgLogicalNameExpression target;

    @Part
    private MgLogicalListExpression arguments;

    public MgLogicalParametrizedExpression() {
    }

    public MgLogicalParametrizedExpression(MgLogicalNameExpression target, MgLogicalListExpression arguments) {
        this.target = target;
        this.arguments = arguments;
    }

    public MgLogicalNameExpression getTarget() {
        return target;
    }

    public void setTarget(MgLogicalNameExpression target) {
        this.target = target;
    }

    public MgLogicalListExpression getArguments() {
        return arguments;
    }

    public void setArguments(MgLogicalListExpression arguments) {
        this.arguments = arguments;
    }
}
