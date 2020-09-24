package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgGroupExpression extends MgExpression {
    @Mandatory @Part
    private final List<MgExpression> expressions;

    public MgGroupExpression(List<MgExpression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        for(MgExpression expression : expressions){
            expression.run(functionObject);
        }
    }
}
