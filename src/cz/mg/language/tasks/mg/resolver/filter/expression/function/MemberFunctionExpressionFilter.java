package cz.mg.language.tasks.mg.resolver.filter.expression.function;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.Todo;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.component.structured.StructuredTypeContext;


public class MemberFunctionExpressionFilter extends FunctionExpressionFilter {
    public MemberFunctionExpressionFilter(
        @Optional StructuredTypeContext targetContext,
        @Mandatory ReadableText requiredName,
        @Optional ExpectedParentInput destination,
        @Optional MgExpression target,
        @Optional MgExpression parameters
    ) {
        super(targetContext, requiredName, destination, target, parameters);

        if(target.getOutputConnectors().count() < 1){
            throw new LanguageException("Target expression has no output.");
        }

        if(target.getOutputConnectors().count() > 1){
            throw new LanguageException("Target expression has multiple output.");
        }

        new Todo(); // todo - verify if this is correct
        if(target.getOutputConnectors().getFirst().getDatatype().getType() != targetContext.getComponent()){
            throw new RuntimeException();
        }
    }

    @Override
    protected @Optional MgFunction filter(@Optional MgFunction function) {
        return filterByParameters(filterByTarget(filterByDestination(function)));
    }

    private @Optional MgFunction filterByTarget(@Optional MgFunction function){
        if(function == null){
            return null;
        }

        if(leftSourceOutputInterface != null){
            MgDatatype inputDatatype = function.getInputVariables().getFirst().getDatatype();
            MgDatatype outputDatatype = leftSourceOutputInterface.getFirst().getDatatype();
            if(!MgDatatype.isCompatible(inputDatatype, outputDatatype)) return null;
        }

        return function;
    }

    private @Optional MgFunction filterByParameters(@Optional MgFunction function){
        if(function == null){
            return null;
        }

        if(rightSourceOutputInterface != null){
            if(function.getInputVariables().count() != rightSourceOutputInterface.count() + 1) return null;
            for(int i = 1; i < function.getInputVariables().count(); i++){
                MgDatatype destinationDatatype = function.getInputVariables().get(i).getDatatype();
                MgDatatype sourceDatatype = rightSourceOutputInterface.get(i-1).getDatatype();
                if(!MgDatatype.isCompatible(destinationDatatype, sourceDatatype)) return null;
            }
        }

        return function;
    }
}
