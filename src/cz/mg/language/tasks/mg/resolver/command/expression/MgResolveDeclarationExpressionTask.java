package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalDeclarationExpression;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.other.MgDatatype;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.ClassFilter;


public class MgResolveDeclarationExpressionTask extends MgResolveExpressionTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalDeclarationExpression logicalExpression;

    @Output
    private Expression expression;

    public MgResolveDeclarationExpressionTask(CommandContext context, MgLogicalDeclarationExpression logicalExpression) {
        this.context = context;
        this.logicalExpression = logicalExpression;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    protected void onRun() {
        expression = new Expression(logicalExpression);
        expression.getDeclaredVariables().addLast(
            new MgVariable(
                logicalExpression.getName(),
                new MgDatatype(
                    resolveType(logicalExpression.getDatatype().getName()),
                    MgDatatype.Storage.valueOf(logicalExpression.getDatatype().getStorage().name()),
                    MgDatatype.Requirement.valueOf(logicalExpression.getDatatype().getRequirement().name())
                )
            )
        );
        expression.setInput(io());
        expression.setOutput(io(expression.getDeclaredVariables()));
    }

    private MgType resolveType(ReadableText name){
        return new ClassFilter<>(context, name, MgType.class).find();
    }
}
