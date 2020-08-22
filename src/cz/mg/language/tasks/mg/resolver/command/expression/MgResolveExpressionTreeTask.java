package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
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
    private static final ReadableText DOT = new ReadonlyText(".");
    private static final ReadableText COMMA = new ReadonlyText(",");

    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalClumpExpression logicalGroupExpression;

    @Output
    private MgLogicalCallExpression logicalCallExpression;

    @Subtask
    private final List<MgResolveExpressionTreeTask> subtasks = new List<>();

    public MgResolveExpressionTreeTask(CommandContext context, MgLogicalClumpExpression logicalGroupExpression) {
        this.context = context;
        this.logicalGroupExpression = logicalGroupExpression;
    }

    public MgLogicalCallExpression getLogicalCallExpression() {
        return logicalCallExpression;
    }

    @Override
    protected void onRun() {
        List<Operator> operators = createOperators(logicalGroupExpression);

        resolveFunctionCalls(operators);
        resolveMemberCalls(operators);
        resolveOperatorCalls(operators);
        resolveGroupCalls(operators);

        if(operators.count() != 1) {
            throw new LanguageException("Illegal expression.");
        }

        if(!(operators.getFirst().getExpression() instanceof MgLogicalCallExpression)) {
            throw new LanguageException("Illegal expression.");
        }

        logicalCallExpression = (MgLogicalCallExpression) operators.getFirst().getExpression();
    }

    private void resolveFunctionCalls(List<Operator> operators){
        for(
            ListItem<Operator> operatorItem = operators.getFirstItem();
            operatorItem != null;
            operatorItem = operatorItem.getNextItem()
        ){
            ttodo; // todo - two variable calls next to each other
        }
    }

    private void resolveMemberCalls(List<Operator> operators){
        for(
            ListItem<Operator> operatorItem = operators.getFirstItem();
            operatorItem != null;
            operatorItem = operatorItem.getNextItem()
        ){
            if(isDot(operatorItem)){
                mergeBinary(operatorItem, MgLogicalMemberCallExpression::new);
            }
        }
    }

    private boolean isDot(ListItem<Operator> item){
        return isOperator(item, DOT);
    }

    private void resolveOperatorCalls(List<Operator> operators){
        OperatorCache operatorCache = context.getOperatorCache();
        for(int p = operatorCache.getMaxPriority(); p >= operatorCache.getMinPriority(); p--){
            List<MgFunction> functions = operatorCache.getFunctions(p);
            for(
                ListItem<Operator> operatorItem = operators.getFirstItem();
                operatorItem != null;
                operatorItem = operatorItem.getNextItem()
            ){
                todo;
            }
        }
    }

    private void resolveGroupCalls(List<Operator> operators){
        for(
            ListItem<Operator> operatorItem = operators.getFirstItem();
            operatorItem != null;
            operatorItem = operatorItem.getNextItem()
        ){
            if(isComma(operatorItem)){
                mergeBinary(operatorItem, MgLogicalGroupCallExpression::new);
            }
        }
    }

    private boolean isComma(ListItem<Operator> item){
        return isOperator(item, COMMA);
    }

    private boolean isOperator(ListItem<Operator> item, ReadableText name){
        MgLogicalExpression logicalExpression = item.get().getExpression();
        if(logicalExpression instanceof MgLogicalOperatorExpression){
            return ((MgLogicalOperatorExpression) logicalExpression).getSigns().equals(name);
        }
        return false;
    }

    private void mergeBinary(ListItem<Operator> item, LogicalBinaryExpressionCallFactory factory){
        ListItem<Operator> leftItem = item.getPreviousItem();
        ListItem<Operator> rightItem = item.getNextItem();
        item.setData(new LeafOperator(factory.create(
            getOperandCall(leftItem, "left"),
            getOperandCall(rightItem, "right")
        )));
        leftItem.remove();
        rightItem.remove();
    }

    private void mergeUnaryLeft(ListItem<Operator> item, LogicalUnaryExpressionCallFactory factory){
        ListItem<Operator> rightItem = item.getNextItem();
        item.setData(new LeafOperator(factory.create(
            getOperandCall(rightItem, "right")
        )));
        rightItem.remove();
    }

    private void mergeUnaryRight(ListItem<Operator> item, LogicalUnaryExpressionCallFactory factory){
        ListItem<Operator> leftItem = item.getPreviousItem();
        item.setData(new LeafOperator(factory.create(
            getOperandCall(leftItem, "left")
        )));
        leftItem.remove();
    }

    private interface LogicalBinaryExpressionCallFactory {
        MgLogicalCallExpression create(MgLogicalCallExpression leftExpression, MgLogicalCallExpression rightExpression);
    }

    private interface LogicalUnaryExpressionCallFactory {
        MgLogicalCallExpression create(MgLogicalCallExpression expression);
    }

    private MgLogicalCallExpression getOperandCall(ListItem<Operator> item, String sideLabel){
        if(item != null){
            MgLogicalExpression logicalExpression = item.get().getExpression();
            if(logicalExpression instanceof MgLogicalCallExpression){
                return (MgLogicalCallExpression) logicalExpression;
            } else {
                throw new LanguageException("Illegal " + sideLabel + " operand.");
            }
        } else {
            throw new LanguageException("Missing " + sideLabel + " operand.");
        }
    }

    private List<Operator> createOperators(MgLogicalClumpExpression logicalGroupExpression){
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
            return createVariableCallExpression((MgLogicalVariableCallExpression) logicalExpression);
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
        if(logicalExpression instanceof MgLogicalClumpExpression){
            subtasks.addLast(new MgResolveExpressionTreeTask(context, (MgLogicalClumpExpression) logicalExpression));
            subtasks.getLast().run();
            return subtasks.getLast().getLogicalCallExpression();
        } else {
            return logicalExpression;
        }
    }
}
