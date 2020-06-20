package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalParametrizedExpression extends MgLogicalExpression {
    @Value
    private MgLogicalNameExpression target;

    @Part
    private List<MgLogicalExpression> arguments;

    @Value
    private boolean closed = false;

    public MgLogicalParametrizedExpression() {
    }

    public MgLogicalParametrizedExpression(MgLogicalNameExpression target, List<MgLogicalExpression> arguments) {
        this.target = target;
        this.arguments = arguments;
    }

    public MgLogicalNameExpression getTarget() {
        return target;
    }

    public void setTarget(MgLogicalNameExpression target) {
        this.target = target;
    }

    public List<MgLogicalExpression> getArguments() {
        return arguments;
    }

    public void setArguments(List<MgLogicalExpression> arguments) {
        this.arguments = arguments;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
