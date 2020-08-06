package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalOperatorExpression;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public class MgResolveOperatorExpressionTask extends MgResolveExpressionTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalOperatorExpression logicalOperatorExpression;

    @Input
    private final Expression parent;

    @Output
    private Expression expression;

    @Subtask
    private List<MgResolveExpressionTask> resolveExpressionTasks = new List<>();

    public MgResolveOperatorExpressionTask(CommandContext context, MgLogicalOperatorExpression logicalExpression, Expression parent) {
        this.context = context;
        this.logicalOperatorExpression = logicalExpression;
        this.parent = parent;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    protected void onRun() {
        todo;
//        // initialize list of elements
//        // resolve all expressions except operators
//        List elements = new List();
//        elements.addCollectionLast(logicalOperatorExpression.getExpressions());
//        for(MgLogicalExpression logicalExpression : logicalOperatorExpression.getExpressions()){
//            MgResolveExpressionTask subtask = MgResolveExpressionTask.create(context, logicalExpression, true);
//            if(subtask != null){
//                if(!(subtask instanceof MgResolveNameExpressionTask)){
//                    resolveExpressionTasks.addLast(subtask);
//                    subtask.run();
//                    elements.addLast(subtask.getExpression());
//                } else {
//                    elements.addLast(logicalExpression);
//                }
//            } else {
//                elements.addLast(logicalExpression);
//            }
//        }
//
//        // resolve operator expressions
//        OperatorCache operatorCache = context.getOperatorCache();
//        for(int p = operatorCache.getFunctions().count() - 1; p >= 0; p--){
//            List<MgFunction> functions = operatorCache.getFunctions().get(p);
//            for(
//                ListItem elementItem = elements.getFirstItem();
//                elementItem != null;
//                elementItem = elementItem.getNextItem()
//            ){
//                //todo;
//            }
//        }
//
//        // operator expressions need to form a tree structure with one root expression
//        // resolve the root expression
//        if(elements.count() <= 0){
//            throw new RuntimeException("Missing operator expression.");
//        } else if(elements.count() == 1){
//            if(elements.getFirst() instanceof Expression){
//                this.expression = (Expression) elements.getFirst();
//            } else {
//                throw new LanguageException("Unresolved operator expression.");
//            }
//        } else {
//            throw new LanguageException("Illegal operator expression.");
//        }
    }
}
