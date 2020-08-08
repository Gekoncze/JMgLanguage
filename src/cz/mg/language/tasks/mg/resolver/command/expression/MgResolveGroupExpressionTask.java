package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.command.expression.operator.Operator;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.contexts.OperatorCache;


public class MgResolveGroupExpressionTask extends MgResolverTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalGroupExpression logicalGroupExpression;

    @Output
    private MgLogicalExpression logicalExpression;

    @Subtask
    private final List<MgResolveGroupExpressionTask> subtasks = new List<>();

    public MgResolveGroupExpressionTask(CommandContext context, MgLogicalGroupExpression logicalGroupExpression) {
        this.context = context;
        this.logicalGroupExpression = logicalGroupExpression;
    }

    public MgLogicalExpression getLogicalExpression() {
        return logicalExpression;
    }

    @Override
    protected void onRun() {
        List<Operator> operators = createOperators(logicalGroupExpression);

        OperatorCache operatorCache = context.getOperatorCache();
        for(int p = operatorCache.getFunctions().count() - 1; p >= 0; p--){
            List<MgFunction> functions = operatorCache.getFunctions().get(p);
            for(
                ListItem operatorItem = operators.getFirstItem();
                operatorItem != null;
                operatorItem = operatorItem.getNextItem()
            ){
                //todo;
            }
        }

        logicalExpression = ?;
    }

    private List<Operator> createOperators(MgLogicalGroupExpression logicalGroupExpression){
        List<Operator> operators = new List<>();
        for(MgLogicalExpression expression : logicalGroupExpression.getExpressions()){
            if(expression instanceof MgLogicalGroupExpression){
                subtasks.addLast(new MgResolveGroupExpressionTask(context, (MgLogicalGroupExpression) expression));
                subtasks.getLast().run();
                expression = subtasks.getLast().getLogicalExpression();
                operators
            }

            else if(expression instanceof MgLogicalNameExpression){
                todo;
            }

            else if(expression instanceof MgLogicalOperatorExpression){
                todo;
            }

            else if(expression instanceof MgLogicalValueExpression){
                todo;
            }

            else {
                throw new LanguageException("Unexpected expression " + expression.getClass().getSimpleName() + " in group.");
            }
        }
        return operators;
    }
}
