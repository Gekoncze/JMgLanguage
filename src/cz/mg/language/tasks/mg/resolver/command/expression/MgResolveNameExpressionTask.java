package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.array.Array;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalNameExpression;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.MgCreateFunctionInstruction;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.ClassFilter;


public class MgResolveNameExpressionTask extends MgResolveExpressionTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalNameExpression logicalExpression;

    @Output
    private Expression expression;

    public MgResolveNameExpressionTask(CommandContext context, MgLogicalNameExpression logicalExpression) {
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
        expression.setInput(io());
        MgComponent component = resolveName(logicalExpression.getName());
        if(component instanceof MgVariable){
            MgVariable variable = (MgVariable) component;
            expression.setOutput(io(variable));
        } else if(component instanceof MgFunction){
            MgFunction function = (MgFunction) component;
            expression.setOutput(function.getOutput());
            expression.getInstructions().addLast(new MgCreateFunctionInstruction(function, new Array<>()));
        } else {
            throw new RuntimeException();
        }
    }

    private MgComponent resolveName(ReadableText name){
        return new ClassFilter<>(context, name, MgVariable.class, MgFunction.class).find();
    }
}
