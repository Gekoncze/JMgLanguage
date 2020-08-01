package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
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
        List elements = new List();
        elements.addCollectionLast(logicalOperatorExpression.getExpressions());

        OperatorCache operatorCache = context.getOperatorCache();

        for(int i = -1; i < operatorCache.getFunctions().count(); i++){
            for(MgLogicalExpression logicalExpression : logicalOperatorExpression.getExpressions()){
                if(i == -1){
                    MgResolveExpressionTask subtask = MgResolveExpressionTask.create(context, logicalExpression, true);
                    if(subtask != null){
                        resolveExpressionTasks.addLast(subtask);
                        subtask.run();
                        elements.addLast(subtask.getExpression());
                    } else {
                        elements.addLast(logicalExpression);
                    }
                } else {
                    List<MgFunction> functions = operatorCache.getFunctions().get(i);
                    todo;
                }
            }
        }
    }
}
