package cz.mg.language.entities.mg.runtime.parts.expressions.member;

import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public abstract class MgMemberExpression extends MgExpression {
    public MgMemberExpression() {
    }

    public abstract void run(MgFunctionObject functionObject, MgClassObject classObject);
}
