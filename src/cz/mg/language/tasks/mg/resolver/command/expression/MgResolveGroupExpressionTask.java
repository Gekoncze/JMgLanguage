package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalGroupExpression;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.contexts.OperatorCache;


public class MgResolveGroupExpressionTask extends MgResolverTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalGroupExpression logicalGroupExpression;

    @Output
    private MgLogicalExpression logicalExpression;

    public MgResolveGroupExpressionTask(CommandContext context, MgLogicalGroupExpression logicalGroupExpression) {
        this.context = context;
        this.logicalGroupExpression = logicalGroupExpression;
    }

    public MgLogicalExpression getLogicalExpression() {
        return logicalExpression;
    }

    @Override
    protected void onRun() {


        OperatorCache operatorCache = context.getOperatorCache();
        for(int p = operatorCache.getFunctions().count() - 1; p >= 0; p--){
            List<MgFunction> functions = operatorCache.getFunctions().get(p);
            if(p == 0) functions.addCollectionLast(todo);
            for(
                ListItem elementItem = elements.getFirstItem();
                elementItem != null;
                elementItem = elementItem.getNextItem()
            ){
                //todo;
            }
        }
    }
}
