package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.Operators;
import cz.mg.language.entities.mg.logical.parts.MgLogicalDatatype;
import cz.mg.language.entities.mg.logical.parts.expressions.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.*;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalBinaryOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalEmptyCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalLunaryOperatorCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.operator.MgLogicalRunaryOperatorCallExpression;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.MgOperatorInfo;
import cz.mg.language.tasks.mg.builder.part.MgBuildDeclarationTask;
import cz.mg.language.tasks.mg.resolver.MgResolveTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.command.utilities.OperatorCache;
import cz.mg.language.tasks.mg.resolver.link.MgResolveVariableDatatypeTask;


public class MgResolveExpressionTreeTask extends MgResolveTask {
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

        resolveDeclarations(expressions);
        resolveFunctionCalls(expressions);
        resolveMemberCalls(expressions);
        resolveOperatorCalls(expressions);
        resolveGroupCalls(expressions);

        if(expressions.count() == 0){
            expressions.addLast(new MgLogicalEmptyCallExpression());
        }

        if(expressions.count() != 1) {
            throw new LanguageException("Illegal expression.");
        }

        if(!(expressions.getFirst() instanceof MgLogicalCallExpression)) {
            throw new LanguageException("Illegal expression.");
        }

        logicalCallExpression = (MgLogicalCallExpression) expressions.getFirst();
    }

    private void resolveDeclarations(List<MgLogicalExpression> operators){
        for(
            ListItem<MgLogicalExpression> item = operators.getFirstItem();
            item != null;
            item = item.getNextItem()
        ){
            if(isPlainName(item)){
                if(isOperator(item.getNextItem())){
                    if(isPlainName(item.getNextItem().getNextItem())){
                        ReadableText typeName = ((MgLogicalNameCallExpression)item.get()).getName();
                        ReadableText operator = ((MgLogicalOperatorExpression)item.getNext()).getName();
                        ReadableText name = ((MgLogicalNameCallExpression)item.getNextItem().getNext()).getName();
                        MgLogicalDatatype logicalDatatype = MgBuildDeclarationTask.createDatatype(typeName, operator);
                        if(logicalDatatype != null){
                            MgResolveVariableDatatypeTask task = new MgResolveVariableDatatypeTask(context, logicalDatatype);
                            task.run();
                            MgDatatype datatype = task.getDatatype();

                            context.getCommand().getDeclaredVariables().addLast(
                                context.getVariableHelper().nextDeclaredVariable(name, datatype)
                            );

                            mergeBinary(
                                item.getNextItem(),
                                (left, right) -> new MgLogicalNameCallExpression(name)
                            );
                        }
                    }
                }
            }
        }
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
            if(isMemberAccessOperator(item)){
                mergeBinary(item, MgResolveExpressionTreeTask::createMemberNameCallExpression);
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
                    MgOperatorInfo operator = logicalOperatorExpression.getOperator();
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
            if(isGroupOperator(item)){
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

    private boolean isMemberAccessOperator(ListItem<MgLogicalExpression> item){
        return isOperator(item, Operators.MEMBER_ACCESS);
    }

    private boolean isGroupOperator(ListItem<MgLogicalExpression> item){
        return isOperator(item, Operators.GROUP);
    }

    private boolean isOperator(ListItem<MgLogicalExpression> item, ReadableText name){
        if(item == null) return false;
        if(item.get() instanceof MgLogicalOperatorExpression){
            return ((MgLogicalOperatorExpression) item.get()).getName().equals(name);
        }
        return false;
    }

    private boolean isOperator(ListItem<MgLogicalExpression> item){
        if(item == null) return false;
        if(item.get() instanceof MgLogicalOperatorExpression){
            return true;
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

    private List<MgLogicalExpression> prepareExpressions(MgLogicalClumpExpression logicalClumpExpression){
        List<MgLogicalExpression> expressions = new List<>();
        for(MgLogicalExpression logicalExpression : logicalClumpExpression.getExpressions()){
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
        MgOperatorInfo operator = findOperator(expression.getName());
        if(operator != null){
            return new MgLogicalOperatorExpression(expression.getName(), operator);
        } else {
            return expression;
        }
    }

    private MgLogicalExpression prepareOperatorExpression(MgLogicalOperatorExpression expression){
        MgOperatorInfo operator = findOperator(expression.getName());
        if(operator != null){
            expression.setOperator(operator);
            return expression;
        } else {
            throw new LanguageException("Could not find operator '" + expression.getName() + "'.");
        }
    }

    private MgOperatorInfo findOperator(ReadableText name){
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

    private static MgLogicalMemberNameCallExpression createMemberNameCallExpression(
        MgLogicalCallExpression left,
        MgLogicalCallExpression right
    ){
        if(right instanceof MgLogicalNameCallExpression){
            MgLogicalNameCallExpression name = (MgLogicalNameCallExpression) right;
            return new MgLogicalMemberNameCallExpression(left, name.getName(), name.getExpression());
        } else {
            throw new LanguageException("Member access must be followed by a name.");
        }
    }
}
