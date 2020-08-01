package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalOperatorExpression;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.contexts.OperatorCache;


public class MgResolveOperatorExpressionTask extends MgResolveExpressionTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalOperatorExpression logicalOperatorExpression;

    @Output
    private Expression expression;

    @Subtask
    private List<MgResolveExpressionTask> resolveExpressionTasks = new List<>();

    public MgResolveOperatorExpressionTask(CommandContext context, MgLogicalOperatorExpression logicalExpression) {
        this.context = context;
        this.logicalOperatorExpression = logicalExpression;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    protected void onRun() {
        // initialize list of elements
        // resolve all expressions except operators
        boolean hasOperator = false;
        List elements = new List();
        elements.addCollectionLast(logicalOperatorExpression.getExpressions());
        for(MgLogicalExpression logicalExpression : logicalOperatorExpression.getExpressions()){
            MgResolveExpressionTask subtask = MgResolveExpressionTask.create(context, logicalExpression, true);
            if(subtask != null){
                resolveExpressionTasks.addLast(subtask);
                subtask.run();
                elements.addLast(subtask.getExpression());
            } else {
                elements.addLast(logicalExpression);
                hasOperator = true;
            }
        }

        // resolve operator expressions if there are any
        if(hasOperator){
            OperatorCache operatorCache = context.getOperatorCache();
            for(int p = operatorCache.getFunctions().count() - 1; p >= 0; p--){
                List<MgFunction> functions = operatorCache.getFunctions().get(p);
                for(
                    ListItem elementItem = elements.getFirstItem();
                    elementItem != null;
                    elementItem = elementItem.getNextItem()
                ){
                    todo;
                }
            }
        }

        // operator expressions need to form a tree structure with one root expression
        // resolve the root expression
        if(elements.count() <= 0){
            throw new LanguageException("Missing expression.");
        } else if(elements.count() == 1){
            if(elements.getFirst() instanceof Expression){
                this.expression = (Expression) elements.getFirst();
            } else {
                throw new LanguageException("Unresolved expression.");
            }
        } else {
            throw new LanguageException("Illegal expression.");
        }
    }
}
