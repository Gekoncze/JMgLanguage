package cz.mg.language.tasks.mg.resolver.command.expression.name.instance;

import cz.mg.language.Todo;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalMemberNameCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.executable.CommandContext;


public class MgResolveInstanceFunctionExpressionTask extends MgResolveInstanceNameExpressionTask {
    public MgResolveInstanceFunctionExpressionTask(
        CommandContext context,
        MgLogicalMemberNameCallExpression logicalExpression,
        ExpectedParentInput parent
    ) {
        super(context, logicalExpression, parent);
    }

    @Override
    public MgExpression getExpression() {
        new Todo();
        return null;
    }
}
