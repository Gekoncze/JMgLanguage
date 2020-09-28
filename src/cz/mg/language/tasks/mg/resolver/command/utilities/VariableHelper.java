package cz.mg.language.tasks.mg.resolver.command.utilities;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.components.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.utilities.helpers.SimpleVariableHelper;


public interface VariableHelper {
    void sink();
    void raise();
    MgFunctionVariable nextDeclaredVariable(ReadableText name, MgDatatype datatype);
    MgFunctionVariable nextExpressionVariable(MgDatatype datatype);
    MgFunctionVariable nextExpressionVariable(
        Node parent,
        InputConnector parentInputConnector,
        Node child,
        OutputConnector childOutputConnector
    );

    static VariableHelper create(MgFunction function){
        return new SimpleVariableHelper(function); // todo - return optimized version instead
    }
}
