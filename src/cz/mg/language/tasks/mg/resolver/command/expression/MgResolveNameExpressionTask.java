package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalNameCallExpression;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgMemberVariable;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes.FunctionNode;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes.LocalVariableNode;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes.MemberVariableNode;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;
import cz.mg.language.tasks.mg.resolver.filter.ExpressionFilter;


public class MgResolveNameExpressionTask extends MgResolveExpressionTask {
    @Input
    private final MgLogicalNameCallExpression logicalExpression;

    @Output
    private Node node;

    public MgResolveNameExpressionTask(CommandContext context, MgLogicalNameCallExpression logicalExpression, Node parent) {
        super(context, parent);
        this.logicalExpression = logicalExpression;
    }

    @Override
    protected Node getNode() {
        return node;
    }

    @Override
    protected ReadableCollection<MgLogicalCallExpression> getLogicalChildren() {
        return null;
    }

    @Override
    protected void onResolveEnter(InputInterface parentInputInterface) {
    }

    @Override
    protected void onResolveLeave(InputInterface parentInputInterface, OutputInterface childrenOutputInterface) {
        ExpressionFilter filter = new ExpressionFilter(
            context,
            logicalExpression.getName(),
            parentInputInterface,
            childrenOutputInterface
        );
        MgComponent component = filter.find();
        if(component instanceof MgLocalVariable){
            node = new LocalVariableNode((MgLocalVariable) component);
        } else if(component instanceof MgMemberVariable){
            node = new MemberVariableNode((MgMemberVariable) component);
        } else if(component instanceof MgFunction){
            node = new FunctionNode((MgFunction) component);
        } {
            throw new RuntimeException();
        }
    }
}
