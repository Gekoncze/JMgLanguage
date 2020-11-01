package cz.mg.language.tasks.mg.resolver.filter.expression.operator;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgBinaryOperator;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.Context;


public class BinaryOperatorExpressionFilter extends OperatorExpressionFilter<MgBinaryOperator> {
    public BinaryOperatorExpressionFilter(
        @Optional Context context,
        @Mandatory ReadableText requiredName,
        @Optional ExpectedParentInput destination,
        @Optional MgExpression leftSource,
        @Optional MgExpression rightSource,
        @Mandatory int replication
    ) {
        super(context, requiredName, MgBinaryOperator.class, destination, leftSource, rightSource, replication);
    }

    @Override
    protected @Optional MgBinaryOperator filter(@Optional MgBinaryOperator operator) {
        return filterBySource(filterByDestination(operator));
    }

    private @Optional MgBinaryOperator filterBySource(@Optional MgBinaryOperator operator){
        if(operator == null){
            return null;
        }

        if(leftSourceOutputInterface != null && rightSourceOutputInterface != null){
            if(operator.getInputVariables().count() != 2) return null;
            MgDatatype sourceDatatypeLeft = leftSourceOutputInterface.get(replication).getDatatype();
            MgDatatype sourceDatatypeRight = rightSourceOutputInterface.get(replication).getDatatype();
            MgDatatype destinationDatatypeLeft = operator.getOutputVariables().getFirst().getDatatype();
            MgDatatype destinationDatatypeRight = operator.getOutputVariables().getLast().getDatatype();
            if(!MgDatatype.isCompatible(destinationDatatypeLeft, sourceDatatypeLeft)) return null;
            if(!MgDatatype.isCompatible(destinationDatatypeRight, sourceDatatypeRight)) return null;
        }

        return operator;
    }
}
