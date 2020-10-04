package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;


public class RunaryOperatorNode extends UnaryOperatorNode {
    public RunaryOperatorNode(List<MgFunction> functions) {
        super(functions);
    }
}
