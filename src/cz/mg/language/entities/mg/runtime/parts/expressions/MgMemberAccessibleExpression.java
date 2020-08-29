package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public abstract class MgMemberAccessibleExpression extends MgExpression {
    public MgMemberAccessibleExpression() {
    }

    public abstract void run(MgFunctionObject functionObject, MgClassObject classObject);
}
