package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.expressions.basic.MgFunctionExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.member.MgMemberFunctionExpression;


public class MemberFunctionNode extends MemberNode {
    @Mandatory @Link
    private final MgFunction function;

    public MemberFunctionNode(@Mandatory MgFunction function) {
        super(createInputInterface(function), createOutputInterface(function));
        this.function = function;
    }

    @Override
    public MgFunctionExpression createExpression(){
        return new MgMemberFunctionExpression(
            function,
            createExpressions(),
            gatherInputOffset(),
            gatherOutputOffset()
        );
    }
}
