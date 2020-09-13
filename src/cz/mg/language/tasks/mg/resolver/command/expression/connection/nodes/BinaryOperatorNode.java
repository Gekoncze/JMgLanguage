package cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes;

import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgBinaryOperatorExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class BinaryOperatorNode extends OperatorNode {
    public BinaryOperatorNode(MgFunction function, int replication) {
        super(function, replication);
    }

    @Override
    public MgExpression createExpression() {
        return new MgBinaryOperatorExpression(
            function,
            createLeftExpressions(),
            createRightExpressions(),
            gatherLeftInputOffset(),
            gatherRightInputOffset(),
            gatherOutputOffset()
        );
    }
}
