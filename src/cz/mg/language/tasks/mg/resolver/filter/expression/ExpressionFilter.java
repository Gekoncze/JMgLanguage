package cz.mg.language.tasks.mg.resolver.filter.expression;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.filter.Filter;


public abstract class ExpressionFilter<C extends MgComponent> extends Filter<C> {
    @Optional @Link
    protected final ReadableArray<@Optional @Value MgDatatype> destinationInputInterface;

    @Optional @Link
    protected final ReadableArray<@Mandatory @Link MgOutputConnector> leftSourceOutputInterface;

    @Optional @Link
    protected final ReadableArray<@Mandatory @Link MgOutputConnector> rightSourceOutputInterface;

    public ExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Mandatory Class<C> requiredType,
        @Optional ExpectedParentInput destination,
        @Optional MgExpression leftSource,
        @Optional MgExpression rightSource
    ) {
        super(context, requiredName, requiredType);
        this.destinationInputInterface = new Array<>(destination.getDatatypes());
        this.leftSourceOutputInterface = sourceInterface(leftSource);
        this.rightSourceOutputInterface = sourceInterface(rightSource);
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
