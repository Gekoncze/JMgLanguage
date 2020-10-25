package cz.mg.language.entities.mg.runtime.parts.expressions.operator.assignment;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.language.Todo;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgReferenceAssignmentOperatorExpression extends MgAssignmentOperatorExpression {
    public MgReferenceAssignmentOperatorExpression(MgExpression leftExpression, MgExpression rightExpression) {
        super(createInputInterface(), leftExpression, rightExpression);
    }

    @Override
    protected MgCache createCache() {
        new Todo();
        return null;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        super.run(functionInstance);
        // nothing to do
    }

    public static ReadableArray<MgInputConnector> createInputInterface(){
        return new Array<>();
    }
}
