package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.MgCreateFunctionInstruction;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.ExpressionFilter;


public class MgResolveNameExpressionTask extends MgResolveExpressionTask<MgLogicalNameCallExpression> {
    public MgResolveNameExpressionTask(CommandContext context, MgLogicalNameCallExpression logicalExpression, Expression parent) {
        super(context, logicalExpression, parent);
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> onResolveEnter() {
        todo;
    }

    @Override
    protected void onResolveLeave() {
        MgComponent component = resolveName(logicalExpression.getName());
        if(component instanceof MgVariable){
            MgVariable variable = (MgVariable) component;
        } else if(component instanceof MgFunction){
            MgFunction function = (MgFunction) component;
            expression.getInstructions().addLast(new MgCreateFunctionInstruction(function, new Array<>()));
        } else {
            throw new RuntimeException();
        }
    }

    private MgComponent resolveName(ReadableText name){
        return new ExpressionFilter(context, name, new Array<>(), parent.getInput()).find();
    }
}
