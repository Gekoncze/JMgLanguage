package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.command.expression.operator.LeafOperator;
import cz.mg.language.tasks.mg.resolver.command.expression.operator.LunaryOperator;
import cz.mg.language.tasks.mg.resolver.command.expression.operator.Operator;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.contexts.OperatorCache;


public class MgResolveGroupExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalGroupExpression logicalGroupExpression;

    @Output
    private Expression expression;

    @Subtask
    private final List<MgResolveGroupExpressionTask> subtasks = new List<>();

    public MgResolveGroupExpressionTask(CommandContext context, MgLogicalGroupExpression logicalGroupExpression, Expression parent) {
        super(context, parent);
        this.logicalGroupExpression = logicalGroupExpression;
    }

    @Override
    protected ReadableCollection<MgLogicalExpression> onResolveEnter() {
        todo;
    }

    @Override
    protected void onResolveLeave() {
        todo;
    }

    private MgLogicalExpression () {
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
            }

            if(expression instanceof MgLogicalCallExpression){
                operators.addLast(new LeafOperator(expression));
            } else if(expression instanceof MgLogicalNameExpression){
                if(hasParameters()){
                    operators.addLast(new LunaryOperator(expression));
                } else {
                    operators.addLast(new LeafOperator(expression));
                }
            } else if(expression instanceof MgLogicalOperatorExpression){
                if(binary){
                    todo;
                } else if(lunary){
                    todo;
                } else if(runarny){
                    todo;
                } else {
                    throw new RuntimeException();
                }
            } else if(expression instanceof MgLogicalValueExpression){
                operators.addLast(new LeafOperator(expression));
            } else {
                throw new LanguageException("Unexpected expression " + expression.getClass().getSimpleName() + " in group.");
            }
        }
        return operators;
    }
}
