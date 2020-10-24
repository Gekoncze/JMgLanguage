package cz.mg.language.tasks.mg.resolver.filter.expression.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.MgOperatorInfo;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class BinaryOperatorExpressionFilter extends OperatorExpressionFilter {
    public BinaryOperatorExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Optional MgExpression destination,
        @Optional MgExpression leftSource,
        @Optional MgExpression rightSource,
        @Mandatory int replication
    ) {
        super(context, requiredName, destination, leftSource, rightSource, replication);
    }

    @Override
    protected @Optional MgOperator filter(@Optional MgOperator operator) {
        return filterBySource(filterByDestination(filterByPosition(operator)));
    }

    private @Optional MgOperator filterBySource(@Optional MgOperator operator){
        if(operator == null){
            return null;
        }

        if(leftSourceOutputInterface != null && rightSourceOutputInterface != null){
            if(operator.getInput().count() != 2) return null;
            MgDatatype sourceDatatypeLeft = leftSourceOutputInterface.get(replication).getDatatype();
            MgDatatype sourceDatatypeRight = rightSourceOutputInterface.get(replication).getDatatype();
            MgDatatype destinationDatatypeLeft = operator.getOutput().getFirst().getDatatype();
            MgDatatype destinationDatatypeRight = operator.getOutput().getLast().getDatatype();
            if(!MgDatatype.isCompatible(destinationDatatypeLeft, sourceDatatypeLeft)) return null;
            if(!MgDatatype.isCompatible(destinationDatatypeRight, sourceDatatypeRight)) return null;
        }

        return operator;
    }

    private @Optional MgOperator filterByPosition(@Optional MgOperator operator){
        if(operator == null){
            return null;
        }

        if(operator.getInfo().getPosition() != MgOperatorInfo.Position.BINARY){
            return null;
        }

        return operator;
    }
}
