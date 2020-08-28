package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgLogicalBinaryOperatorExpression extends MgExpression {
    @Mandatory @Part
    private final List<MgExpression> leftOperandGroup;

    @Mandatory @Part
    private final List<MgExpression> rightOperandGroup;

    public MgLogicalBinaryOperatorExpression(List<MgExpression> leftOperandGroup, List<MgExpression> rightOperandGroup) {
        if(leftOperandGroup.count() != rightOperandGroup.count()) throw new IllegalArgumentException();
        this.leftOperandGroup = leftOperandGroup;
        this.rightOperandGroup = rightOperandGroup;
    }

    public List<MgExpression> getLeftOperandGroup() {
        return leftOperandGroup;
    }

    public List<MgExpression> getRightOperandGroup() {
        return rightOperandGroup;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo
    }
}
