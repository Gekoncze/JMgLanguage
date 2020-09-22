package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;


public class LunaryOperatorNode extends UnaryOperatorNode {
    public LunaryOperatorNode(List<MgFunction> functions) {
        super(functions);
    }
}
