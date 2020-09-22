package cz.mg.language.entities.mg.runtime.parts.expressions.basic;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgGroupExpression extends MgBasicExpression {
    @Mandatory @Part
    private final List<MgBasicExpression> expressions;

    public MgGroupExpression(List<MgBasicExpression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        for(MgBasicExpression expression : expressions){
            expression.run(functionObject);
        }
    }
}
