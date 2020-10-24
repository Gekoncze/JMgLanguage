package cz.mg.language.tasks.mg.resolver.filter.expression.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.MgOperatorInfo;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class LunaryOperatorExpressionFilter extends UnaryOperatorExpressionFilter {
    public LunaryOperatorExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Optional MgExpression destination,
        @Optional MgExpression rightSource,
        @Mandatory int replication
    ) {
        super(context, requiredName, destination, null, rightSource, replication);
    }

    @Override
    protected @Optional MgOperator filter(@Optional MgOperator operator) {
        return filterBySource(filterByDestination(filterByPosition(operator)));
    }

    private @Optional MgOperator filterBySource(@Optional MgOperator operator){
        if(operator == null){
            return null;
        }

        if(rightSourceOutputInterface != null){
            if(operator.getInput().count() != 1) return null;
            MgDatatype sourceDatatype = rightSourceOutputInterface.get(replication).getDatatype();
            MgDatatype destinationDatatype = operator.getOutput().getFirst().getDatatype();
            if(!MgDatatype.isCompatible(destinationDatatype, sourceDatatype)) return null;
        }

        return operator;
    }

    private @Optional MgOperator filterByPosition(@Optional MgOperator operator){
        if(operator == null){
            return null;
        }

        if(operator.getInfo().getPosition() != MgOperatorInfo.Position.LEFT){
            return null;
        }

        return operator;
    }
}
