package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.command.expression.operator.*;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.contexts.OperatorCache;


public class MgResolveGroupTask extends MgResolverTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalGroupExpression logicalGroupExpression;

    @Output
    private MgLogicalExpression logicalExpression;

    @Subtask
    private final List<MgResolveGroupTask> subtasks = new List<>();

    public MgResolveGroupTask(CommandContext context, MgLogicalGroupExpression logicalGroupExpression) {
        this.context = context;
        this.logicalGroupExpression = logicalGroupExpression;
    }

    public MgLogicalExpression getLogicalExpression() {
        return logicalExpression;
    }

    @Override
    protected void onRun() {
        OperatorCache operatorCache = context.getOperatorCache();
        List<Operator> operators = createOperators(logicalGroupExpression);

        todo; // todo - first gather all function calls - ??? how ? we might need to add a sign for function call ... probably !

        for(int p = operatorCache.getMaxPriority(); p >= operatorCache.getMinPriority(); p--){
            List<MgFunction> functions = operatorCache.getFunctions(p);
            for(
                ListItem operatorItem = operators.getFirstItem();
                operatorItem != null;
                operatorItem = operatorItem.getNextItem()
            ){
                if()
                todo;
            }
        }

        logicalExpression = ?;
    }

    private List<Operator> createOperators(MgLogicalGroupExpression logicalGroupExpression){
        List<Operator> operators = new List<>();
        for(MgLogicalAbstractExpression logicalAbstractExpression : logicalGroupExpression.getExpressions()){
            operators.addLast(createOperator(narrow(logicalAbstractExpression)));
        }
        return operators;
    }

    private Operator createOperator(MgLogicalLessAbstractExpression logicalExpression){
        if(logicalExpression instanceof MgLogicalCallExpression){
            return new LeafOperator((MgLogicalCallExpression) logicalExpression);
        } else if(logicalExpression instanceof MgLogicalNameExpression){
            MgLogicalNameExpression logicalNameExpression = (MgLogicalNameExpression) logicalExpression;
            OperatorCache operatorCache = context.getOperatorCache();
            MgOperator operator = operatorCache.findOperator(logicalNameExpression.getName());
            if(operator != null){
                switch (operator.getType()){
                    case BINARY: return new BinaryOperator(logicalNameExpression, operator.getPriority());
                    case LEFT: return new LunaryOperator(logicalNameExpression, operator.getPriority());
                    case RIGHT: return new RunaryOperator(logicalNameExpression, operator.getPriority());
                    default: throw new RuntimeException();
                }
            } else {
                return new LeafOperator(logicalNameExpression);
            }
        } else if(logicalExpression instanceof MgLogicalOperatorExpression){
            MgLogicalOperatorExpression logicalOperatorExpression = (MgLogicalOperatorExpression) logicalExpression;
            OperatorCache operatorCache = context.getOperatorCache();
            MgOperator operator = operatorCache.findOperator(logicalOperatorExpression.getSigns());
            if(operator != null){
                switch (operator.getType()){
                    case BINARY: return new BinaryOperator(logicalOperatorExpression, operator.getPriority());
                    case LEFT: return new LunaryOperator(logicalOperatorExpression, operator.getPriority());
                    case RIGHT: return new RunaryOperator(logicalOperatorExpression, operator.getPriority());
                    default: throw new RuntimeException();
                }
            } else {
                throw new LanguageException("Could not find operator '" + logicalOperatorExpression.getSigns() + "'.");
            }
        } else if(logicalExpression instanceof MgLogicalValueExpression){
            return new LeafOperator((MgLogicalValueExpression) logicalExpression);
        } else {
            throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " in group.");
        }
    }

    private MgLogicalLessAbstractExpression narrow(MgLogicalAbstractExpression logicalAbstractExpression){
        if(logicalAbstractExpression instanceof MgLogicalGroupExpression){
            subtasks.addLast(new MgResolveGroupTask(context, (MgLogicalGroupExpression) logicalAbstractExpression));
            subtasks.getLast().run();
            return subtasks.getLast().getLogicalExpression();
        } else if(logicalAbstractExpression instanceof MgLogicalExpression) {
            return (MgLogicalExpression) logicalAbstractExpression;
        } else {
            throw new RuntimeException();
        }
    }
}
