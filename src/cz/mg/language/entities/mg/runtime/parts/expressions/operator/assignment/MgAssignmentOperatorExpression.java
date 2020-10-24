package cz.mg.language.entities.mg.runtime.parts.expressions.operator.assignment;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.operator.MgOperatorExpression;


public abstract class MgAssignmentOperatorExpression extends MgOperatorExpression {
    @Mandatory
    @Part
    private final MgExpression leftExpression;

    @Mandatory @Part
    private final MgExpression rightExpression;

    public MgAssignmentOperatorExpression(
        ReadableArray<MgInputConnector> inputConnectors,
        MgExpression leftExpression,
        MgExpression rightExpression
    ) {
        super(inputConnectors, createOutputInterface());
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public MgExpression getLeftExpression() {
        return leftExpression;
    }

    public MgExpression getRightExpression() {
        return rightExpression;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        rightExpression.run(functionInstance);
        leftExpression.run(functionInstance);
    }

    public static ReadableArray<MgOutputConnector> createOutputInterface(){
        return new Array<>();
    }
}
