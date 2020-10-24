package cz.mg.language.tasks.mg.resolver.filter.expression.variable;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class StandaloneVariableExpressionFilter extends VariableExpressionFilter {
    public StandaloneVariableExpressionFilter(
        @Optional CommandContext context,
        @Mandatory ReadableText requiredName,
        @Optional MgExpression destination,
        @Optional MgExpression value
    ) {
        super(context, requiredName, destination, null, value);
    }

    @Override
    protected @Optional MgVariable filter(@Optional MgVariable variable) {
        return filterByValue(filterByDestination(variable));
    }
}
