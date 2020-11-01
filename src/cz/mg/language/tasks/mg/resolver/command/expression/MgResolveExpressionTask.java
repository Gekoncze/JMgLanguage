package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.MgResolveTask;
import cz.mg.language.tasks.mg.resolver.command.expression.name.MgResolveNameExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.name.instance.MgResolveInstanceNameExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.operator.MgResolveOperatorExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.other.MgResolveGroupExpressionTask;
import cz.mg.language.tasks.mg.resolver.command.expression.other.MgResolveValueExpressionTask;
import cz.mg.language.entities.mg.runtime.utilities.DeclarationHelper;
import cz.mg.language.tasks.mg.resolver.command.utilities.ExpectedParentInput;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public abstract class MgResolveExpressionTask extends MgResolveTask {
    @Mandatory @Input
    protected final CommandContext context;

    @Optional @Input
    private final ExpectedParentInput parent;

    public MgResolveExpressionTask(CommandContext context, ExpectedParentInput parent) {
        this.context = context;
        this.parent = parent;
    }

    public @Optional ExpectedParentInput getParent() {
        return parent;
    }

    public @Optional abstract MgExpression getExpression();

    @Override
    protected final void onRun() {
        DeclarationHelper.sink();

        onResolve();

        DeclarationHelper.raise();
    }

    protected abstract void onResolve();

    protected MgExpression resolveChild(MgLogicalCallExpression logicalExpression, ExpectedParentInput parent){
        MgResolveExpressionTask task = MgResolveExpressionTask.create(context, logicalExpression, parent);
        task.run();
        return task.getExpression();
    }

    public static MgResolveExpressionTask create(
        CommandContext context,
        MgLogicalCallExpression logicalExpression,
        ExpectedParentInput parent
    ){
        if(logicalExpression instanceof MgLogicalNameCallExpression) {
            return MgResolveNameExpressionTask.create(context, (MgLogicalNameCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalValueCallExpression){
            return new MgResolveValueExpressionTask(context, (MgLogicalValueCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalOperatorCallExpression){
            return MgResolveOperatorExpressionTask.create(context, (MgLogicalOperatorCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalGroupCallExpression){
            return new MgResolveGroupExpressionTask(context, (MgLogicalGroupCallExpression) logicalExpression, parent);
        }

        if(logicalExpression instanceof MgLogicalMemberNameCallExpression){
            return MgResolveInstanceNameExpressionTask.create(context, (MgLogicalMemberNameCallExpression) logicalExpression, parent);
        }

        throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " for resolve.");
    }
}
