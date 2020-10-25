package cz.mg.language.tasks.mg.resolver.filter.expression.variable;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.Todo;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.component.structured.StructuredTypeContext;


public class MemberVariableExpressionFilter extends VariableExpressionFilter {
    public MemberVariableExpressionFilter(
        @Optional StructuredTypeContext targetContext,
        @Mandatory ReadableText requiredName,
        @Optional MgExpression destination,
        @Optional MgExpression target,
        @Optional MgExpression value
    ) {
        super(targetContext, requiredName, destination, target, value);

        if(target.getCache().getOutputConnectors().count() < 1){
            throw new LanguageException("Target expression has no output.");
        }

        if(target.getCache().getOutputConnectors().count() > 1){
            throw new LanguageException("Target expression has multiple output.");
        }

        new Todo(); // todo - verify if this is correct
        if(target.getCache().getOutputConnectors().getFirst().getDatatype().getType() != targetContext.getComponent()){
            throw new RuntimeException();
        }
    }

    @Override
    protected @Optional MgVariable filter(@Optional MgVariable variable) {
        return filterByValue(filterByTarget(filterByDestination(variable)));
    }

    private @Optional MgVariable filterByTarget(@Optional MgVariable variable){
        return variable; // already "filtered" by target context
    }
}
