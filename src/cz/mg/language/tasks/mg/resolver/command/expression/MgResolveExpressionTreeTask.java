package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalLunaryOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalRunaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.command.utilities.OperatorCache;


public class MgResolveExpressionTreeTask extends MgResolverTask {
    private static final ReadableText DOT = new ReadonlyText(".");
    private static final ReadableText COMMA = new ReadonlyText(",");

    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalClumpExpression logicalClumpExpression;

    @Output
    private MgLogicalCallExpression logicalCallExpression;

    public MgResolveExpressionTreeTask(CommandContext context, MgLogicalClumpExpression logicalClumpExpression) {
        this.context = context;
        this.logicalClumpExpression = logicalClumpExpression;
    }

    public MgLogicalCallExpression getLogicalCallExpression() {
        return logicalCallExpression;
    }

    @Override
    protected void onRun() {
        List<MgLogicalExpression> expressions = prepareExpressions(logicalClumpExpression);

        resolveFunctionCalls(expressions);
        resolveMemberCalls(expressions);
        resolveOperatorCalls(expressions);
        resolveGroupCalls(expressions);

        if(expressions.count() != 1) {
            throw new LanguageException("Illegal expression.");
        }

        if(!(expressions.getFirst() instanceof MgLogicalCallExpression)) {
            throw new LanguageException("Illegal expression.");
        }

        logicalCallExpression = (MgLogicalCallExpression) expressions.getFirst();
    }

    private void resolveFunctionCalls(List<MgLogicalExpression> operators){
        for(
            ListItem<MgLogicalExpression> item = operators.getFirstItem();
            item != null;
            item = item.getNextItem()
        ){
            if(isPlainName(item)){
                if(isCall(item.getNextItem())){
                    // todo - can be simplified - just add expression to already existing name call (dont forget to still remove next)
                    MgLogicalNameCallExpression nameCallExpression = (MgLogicalNameCallExpression) item.get();
                    mergeLunary(
                        item,
                        expression -> new MgLogicalNameCallExpression(
                            nameCallExpression.getName(),
                            expression
                        )
                    );
                }
            }
        }
    }

    private void resolveMemberCalls(List<MgLogicalExpression> operators){
        for(
            ListItem<MgLogicalExpression> item = operators.getFirstItem();
            item != null;
            item = item.getNextItem()
        ){
            if(isDot(item)){
                mergeBinary(item, MgResolveExpressionTreeTask::createMemberAccessCallExpression);
            }
        }
    }

    private void resolveOperatorCalls(List<MgLogicalExpression> operators){
        OperatorCache operatorCache = context.getOperatorCache();
        for(int p = operatorCache.getMaxPriority(); p >= operatorCache.getMinPriority(); p--){
            for(
                ListItem<MgLogicalExpression> item = operators.getFirstItem();
                item != null;
                item = item.getNextItem()
            ){
                MgLogicalExpression expression = item.get();
                if(expression instanceof MgLogicalOperatorExpression){
                    MgLogicalOperatorExpression logicalOperatorExpression = (MgLogicalOperatorExpression) expression;
                    MgOperator operator = logicalOperatorExpression.getOperator();
                    if(operator.getPriority() == p){
                        switch(operator.getType()){
                            case BINARY:
                                mergeBinary(item, (leftOperand, rightOperand) -> {
                                    return new MgLogicalBinaryOperatorCallExpression(
                                        logicalOperatorExpression.getName(),
                                        leftOperand,
                                        rightOperand
                                    );
                                });
                                break;
                            case LEFT:
                                mergeLunary(item, operand -> {
                                    return new MgLogicalLunaryOperatorCallExpression(
                                        logicalOperatorExpression.getName(),
                                        operand
                                    );
                                });
                                break;
                            case RIGHT:
                                mergeRunary(item, operand -> {
                                    return new MgLogicalRunaryOperatorCallExpression(
                                        logicalOperatorExpression.getName(),
                                        operand
                                    );
                                });
                                break;
                            default: throw new RuntimeException();
                        }
                    }
                }
            }
        }
    }

    private void resolveGroupCalls(List<MgLogicalExpression> operators){
        for(
            ListItem<MgLogicalExpression> item = operators.getFirstItem();
            item != null;
            item = item.getNextItem()
        ){
            if(isComma(item)){
                ListItem<MgLogicalExpression> leftItem = item.getPreviousItem();
                if(isGroup(leftItem)){
                    mergeBinary(item, (leftExpression, rightExpression) -> {
                        MgLogicalGroupCallExpression group = (MgLogicalGroupCallExpression) leftExpression;
                        group.getExpressions().addLast(rightExpression);
                        return group;
                    });
                } else {
                    mergeBinary(item, (leftExpression, rightExpression) -> {
                        return new MgLogicalGroupCallExpression(new List<>(leftExpression, rightExpression));
                    });
                }
            }
        }
    }

    private boolean isGroup(ListItem<MgLogicalExpression> item){
        if(item == null) return false;
        return item.get() instanceof MgLogicalGroupCallExpression;
    }

    private boolean isPlainName(ListItem<MgLogicalExpression> item){
        if(item == null) return false;
        if(item.get() instanceof MgLogicalNameCallExpression){
            // todo - might be redundant, parametrized should not be created yet
            return ((MgLogicalNameCallExpression) item.get()).getExpression() == null;
        }
        return false;
    }

    private boolean isCall(ListItem<MgLogicalExpression> item){
        if(item == null) return false;
        return item.get() instanceof MgLogicalCallExpression;
    }

    private boolean isDot(ListItem<MgLogicalExpression> item){
        return isOperator(item, DOT);
    }

    private boolean isComma(ListItem<MgLogicalExpression> item){
        return isOperator(item, COMMA);
    }

    private boolean isOperator(ListItem<MgLogicalExpression> item, ReadableText name){
        if(item == null) return false;
        if(item.get() instanceof MgLogicalOperatorExpression){
            return ((MgLogicalOperatorExpression) item.get()).getName().equals(name);
        }
        return false;
    }

    private void mergeBinary(ListItem<MgLogicalExpression> item, LogicalBinaryExpressionCallFactory factory){
        ListItem<MgLogicalExpression> leftItem = item.getPreviousItem();
        ListItem<MgLogicalExpression> rightItem = item.getNextItem();
        item.setData(factory.create(
            getCall(leftItem, "left"),
            getCall(rightItem, "right")
        ));
        leftItem.remove();
        rightItem.remove();
    }

    private void mergeLunary(ListItem<MgLogicalExpression> item, LogicalUnaryExpressionCallFactory factory){
        ListItem<MgLogicalExpression> rightItem = item.getNextItem();
        item.setData(factory.create(
            getCall(rightItem, "right")
        ));
        rightItem.remove();
    }

    private void mergeRunary(ListItem<MgLogicalExpression> item, LogicalUnaryExpressionCallFactory factory){
        ListItem<MgLogicalExpression> leftItem = item.getPreviousItem();
        item.setData(factory.create(
            getCall(leftItem, "left")
        ));
        leftItem.remove();
    }

    private interface LogicalBinaryExpressionCallFactory {
        MgLogicalCallExpression create(MgLogicalCallExpression leftExpression, MgLogicalCallExpression rightExpression);
    }

    private interface LogicalUnaryExpressionCallFactory {
        MgLogicalCallExpression create(MgLogicalCallExpression expression);
    }

    private MgLogicalCallExpression getCall(ListItem<MgLogicalExpression> item, String sideLabel){
        if(item != null){
            if(item.get() instanceof MgLogicalCallExpression){
                return (MgLogicalCallExpression) item.get();
            } else {
                throw new LanguageException("Illegal " + sideLabel + " operand.");
            }
        } else {
            throw new LanguageException("Missing " + sideLabel + " operand.");
        }
    }

    private List<MgLogicalExpression> prepareExpressions(MgLogicalClumpExpression logicalGroupExpression){
        List<MgLogicalExpression> expressions = new List<>();
        for(MgLogicalExpression logicalExpression : logicalGroupExpression.getExpressions()){
            expressions.addLast(prepareExpression(resolveNestedGroups(logicalExpression)));
        }
        return expressions;
    }

    private MgLogicalExpression prepareExpression(MgLogicalExpression logicalExpression){
        if(logicalExpression instanceof MgLogicalNameCallExpression){
            return prepareOperatorExpression((MgLogicalNameCallExpression) logicalExpression);
        }

        if(logicalExpression instanceof MgLogicalOperatorExpression){
            return prepareOperatorExpression((MgLogicalOperatorExpression) logicalExpression);
        }

        return logicalExpression;
    }

    private MgLogicalExpression prepareOperatorExpression(MgLogicalNameCallExpression expression){
        MgOperator operator = findOperator(expression.getName());
        if(operator != null){
            return new MgLogicalOperatorExpression(expression.getName(), operator);
        } else {
            return expression;
        }
    }

    private MgLogicalExpression prepareOperatorExpression(MgLogicalOperatorExpression expression){
        MgOperator operator = findOperator(expression.getName());
        if(operator != null){
            expression.setOperator(operator);
            return expression;
        } else {
            throw new LanguageException("Could not find operator '" + expression.getName() + "'.");
        }
    }

    private MgOperator findOperator(ReadableText name){
        return context.getOperatorCache().findOperator(name);
    }

    private MgLogicalExpression resolveNestedGroups(MgLogicalExpression logicalExpression){
        if(logicalExpression instanceof MgLogicalClumpExpression){
            MgResolveExpressionTreeTask task = new MgResolveExpressionTreeTask(context, (MgLogicalClumpExpression) logicalExpression);
            task.run();
            return task.getLogicalCallExpression();
        } else {
            return logicalExpression;
        }
    }

    private static MgLogicalMemberAccessCallExpression createMemberAccessCallExpression(
        MgLogicalCallExpression left,
        MgLogicalCallExpression right
    ){
        return new MgLogicalMemberAccessCallExpression(left, (MgLogicalNameCallExpression) right);
    }
}
