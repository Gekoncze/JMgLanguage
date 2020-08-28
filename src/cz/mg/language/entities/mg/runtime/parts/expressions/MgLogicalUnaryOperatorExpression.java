package cz.mg.language.entities.mg.runtime.parts.expressions;


import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgLogicalUnaryOperatorExpression extends MgExpression {
    @Mandatory @Part
    private final List<MgExpression> operandGroup;

    public MgLogicalUnaryOperatorExpression(List<MgExpression> operandGroup) {
        this.operandGroup = operandGroup;
    }

    public List<MgExpression> getOperandGroup() {
        return operandGroup;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo
    }
}
