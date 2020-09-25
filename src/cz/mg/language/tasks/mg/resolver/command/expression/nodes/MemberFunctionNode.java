package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;


public class MemberFunctionNode extends Node {
    @Mandatory @Link
    private final MgFunction function;

    public MemberFunctionNode(@Mandatory MgFunction function) {
        super(FunctionNode.createInputInterface(function), FunctionNode.createOutputInterface(function));
        this.function = function;
    }

    public MgFunction getFunction() {
        return function;
    }
}
