//package cz.mg.language.tasks.mg.resolver.command.expression;
//
//import cz.mg.language.annotations.task.Input;
//import cz.mg.language.annotations.task.Output;
//import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalValueExpression;
//import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
//
//
//public class MgResolveValueExpressionTask extends MgResolveExpressionTask {
//    @Input
//    private final CommandContext context;
//
//    @Input
//    private final MgLogicalValueExpression logicalExpression;
//
//    @Input
//    private final Expression parent;
//
//    @Output
//    private Expression expression;
//
//    public MgResolveValueExpressionTask(CommandContext context, MgLogicalValueExpression logicalExpression, Expression parent) {
//        this.context = context;
//        this.logicalExpression = logicalExpression;
//        this.parent = parent;
//    }
//
//    @Override
//    public Expression getExpression() {
//        return expression;
//    }
//
//    @Override
//    protected void onRun() {
//        //todo;
//    }
//}
