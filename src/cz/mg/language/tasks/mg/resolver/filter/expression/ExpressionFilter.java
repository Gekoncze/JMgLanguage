package cz.mg.language.tasks.mg.resolver.filter.expression;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.filter.Filter;


public abstract class ExpressionFilter<C extends MgComponent> extends Filter<C> {
    @Optional @Link
    protected final ReadableArray<@Mandatory @Link MgInputConnector> destinationInputInterface;

    @Optional @Link
    protected final ReadableArray<@Mandatory @Link MgOutputConnector> leftSourceOutputInterface;

    @Optional @Link
    protected final ReadableArray<@Mandatory @Link MgOutputConnector> rightSourceOutputInterface;

    public ExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Mandatory Class<C> requiredType,
        @Optional MgExpression destination,
        @Optional MgExpression leftSource,
        @Optional MgExpression rightSource
    ) {
        super(context, requiredName, requiredType);
        this.destinationInputInterface = destinationInterface(destination);
        this.leftSourceOutputInterface = sourceInterface(leftSource);
        this.rightSourceOutputInterface = sourceInterface(rightSource);
    }

    public static ReadableArray<MgInputConnector> destinationInterface(MgExpression parent){
        if(parent != null){
            List<MgInputConnector> connectors = new List<>();
            for(MgInputConnector connector : parent.getInputConnectors()){
                if(connector.getConnection() != null){
                    connectors.addLast(connector);
                }
            }
            return new Array<>(connectors);
        } else {
            return null;
        }
    }

    private static ReadableArray<MgOutputConnector> sourceInterface(MgExpression child){
        if(child != null){
            List<MgOutputConnector> connectors = new List<>();
            for(MgOutputConnector connector : child.getOutputConnectors()){
                if(connector.getConnection() != null){
                    connectors.addLast(connector);
                }
            }
            return new Array<>(connectors);
        } else {
            return null;
        }
    }
}
