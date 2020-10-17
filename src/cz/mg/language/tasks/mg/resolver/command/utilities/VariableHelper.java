package cz.mg.language.tasks.mg.resolver.command.utilities;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.helpers.SimpleVariableHelper;


public interface VariableHelper {
    void sink();
    void raise();
    MgFunctionVariable nextDeclaredVariable(ReadableText name, MgDatatype datatype);
    MgFunctionVariable nextExpressionVariable(MgDatatype datatype);
    MgFunctionVariable nextExpressionVariable(
        MgExpression parent,
        MgInputConnector parentInputConnector,
        MgExpression child,
        MgOutputConnector childOutputConnector
    );

    static VariableHelper create(MgFunction function){
        return new SimpleVariableHelper(function); // todo - return optimized version instead
    }
}
