package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveNameExpressionTask extends MgResolveExpressionTask<MgLogicalNameCallExpression> {
    public MgResolveNameExpressionTask(CommandContext context, MgLogicalNameCallExpression logicalExpression, Expression parent) {
        super(context, logicalExpression, parent);
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> onResolveEnter() {
        //todo;
        return null;
    }

    @Override
    protected void onResolveLeave() {
        // todo
//        MgComponent component = resolveName(logicalExpression.getName());
//        if(component instanceof MgVariable){
//            MgVariable variable = (MgVariable) component;
//        } else if(component instanceof MgFunction){
//            MgFunction function = (MgFunction) component;
//            expression.getInstructions().addLast(new MgCreateFunctionInstruction(function, new Array<>()));
//        } else {
//            throw new RuntimeException();
//        }
    }

    private MgComponent resolveName(ReadableText name){
        // todo: return new ExpressionFilter(context, name, new Array<>(), parent.getInput()).find();
        return null;
    }
}
