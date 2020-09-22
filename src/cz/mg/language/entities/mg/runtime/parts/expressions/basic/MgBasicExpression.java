package cz.mg.language.entities.mg.runtime.parts.expressions.basic;

import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public abstract class MgBasicExpression extends MgExpression {
    public MgBasicExpression() {
    }

    public abstract void run(MgFunctionObject functionObject);
}
