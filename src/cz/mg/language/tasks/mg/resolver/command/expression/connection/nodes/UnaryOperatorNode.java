package cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes;

import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgUnaryOperatorExpression;


public class UnaryOperatorNode extends OperatorNode {
    public UnaryOperatorNode(MgFunction function, int replication) {
        super(function, replication);
        if(function.getInput().count() != 1) throw new RuntimeException();
    }

    @Override
    public MgExpression createExpression() {
        return new MgUnaryOperatorExpression(
            function,
            createExpressions(getInput()),
            gatherInputOffset(),
            gatherOutputOffset()
        );
    }
}
