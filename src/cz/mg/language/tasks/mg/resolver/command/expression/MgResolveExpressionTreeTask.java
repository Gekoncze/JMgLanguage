package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.*;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.command.expression.operator.*;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.contexts.OperatorCache;


public class MgResolveExpressionTreeTask extends MgResolverTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalGroupExpression logicalGroupExpression;

    @Output
    private MgLogicalCallExpression logicalCallExpression;

    @Subtask
    private final List<MgResolveExpressionTreeTask> subtasks = new List<>();

    public MgResolveExpressionTreeTask(CommandContext context, MgLogicalGroupExpression logicalGroupExpression) {
        this.context = context;
        this.logicalGroupExpression = logicalGroupExpression;
    }

    public MgLogicalCallExpression getLogicalCallExpression() {
        return logicalCallExpression;
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

        logicalCallExpression = ?;
    }

    private List<Operator> createOperators(MgLogicalGroupExpression logicalGroupExpression){
        List<Operator> operators = new List<>();
        for(MgLogicalExpression logicalAbstractExpression : logicalGroupExpression.getExpressions()){
            operators.addLast(createOperator(resolveNestedGroups(logicalAbstractExpression)));
        }
        return operators;
    }

    private Operator createOperator(MgLogicalExpression logicalExpression){
        if(logicalExpression instanceof MgLogicalFunctionCallExpression){
            return createLeafOperator((MgLogicalFunctionCallExpression)logicalExpression);
        }

        if(logicalExpression instanceof MgLogicalVariableCallExpression){
            createVariableCallExpression((MgLogicalVariableCallExpression) logicalExpression);
        }

        if(logicalExpression instanceof MgLogicalOperatorExpression){
            return createOperatorOperator((MgLogicalOperatorExpression) logicalExpression);
        }

        if(logicalExpression instanceof MgLogicalValueCallExpression){
            return createLeafOperator((MgLogicalValueCallExpression) logicalExpression);
        }

        throw new LanguageException("Unexpected expression " + logicalExpression.getClass().getSimpleName() + " in group.");
    }

    private Operator createVariableCallExpression(MgLogicalVariableCallExpression expression){
        MgOperator operator = findOperator(expression.getName());
        if(operator != null){
            return createOtherOperator(expression, operator);
        } else {
            return new LeafOperator(expression);
        }
    }

    private Operator createOperatorOperator(MgLogicalOperatorExpression expression){
        MgOperator operator = findOperator(expression.getSigns());
        if(operator != null){
            return createOtherOperator(expression, operator);
        } else {
            throw new LanguageException("Could not find operator '" + expression.getSigns() + "'.");
        }
    }

    private MgOperator findOperator(ReadableText name){
        return context.getOperatorCache().findOperator(name);
    }

    private LeafOperator createLeafOperator(MgLogicalCallExpression expression){
        return new LeafOperator(expression);
    }

    private Operator createOtherOperator(MgLogicalExpression expression, MgOperator operator){
        switch (operator.getType()){
            case BINARY: return new BinaryOperator(expression, operator.getPriority());
            case LEFT: return new LunaryOperator(expression, operator.getPriority());
            case RIGHT: return new RunaryOperator(expression, operator.getPriority());
            default: throw new RuntimeException();
        }
    }

    private MgLogicalExpression resolveNestedGroups(MgLogicalExpression logicalExpression){
        if(logicalExpression instanceof MgLogicalGroupExpression){
            subtasks.addLast(new MgResolveExpressionTreeTask(context, (MgLogicalGroupExpression) logicalExpression));
            subtasks.getLast().run();
            return subtasks.getLast().getLogicalCallExpression();
        } else {
            return logicalExpression;
        }
    }
}
