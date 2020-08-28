package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgBinaryOperatorExpression extends MgOperatorExpression {
    @Mandatory @Part
    private final List<MgExpression> leftOperandGroup;

    @Mandatory @Part
    private final List<MgExpression> rightOperandGroup;

    public MgBinaryOperatorExpression(MgFunction function, List<MgExpression> leftOperandGroup, List<MgExpression> rightOperandGroup) {
        super(function);
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
