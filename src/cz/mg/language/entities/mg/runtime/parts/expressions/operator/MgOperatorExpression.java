package cz.mg.language.entities.mg.runtime.parts.expressions.operator;


import cz.mg.collections.array.ReadableArray;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public abstract class MgOperatorExpression extends MgExpression {
    public MgOperatorExpression(
        ReadableArray<MgInputConnector> inputConnectors,
        ReadableArray<MgOutputConnector> outputConnectors
    ) {
        super(inputConnectors, outputConnectors);
    }
}
