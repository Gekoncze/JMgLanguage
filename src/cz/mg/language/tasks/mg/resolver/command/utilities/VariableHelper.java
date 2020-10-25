package cz.mg.language.tasks.mg.resolver.command.utilities;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.command.utilities.helpers.SimpleVariableHelper;


public interface VariableHelper {
    void sink();
    void raise();
    MgInstanceVariable nextDeclaredVariable(ReadableText name, MgDatatype datatype);
    MgInstanceVariable nextExpressionVariable(MgDatatype datatype);

    static VariableHelper create(MgFunction function){
        return new SimpleVariableHelper(function); // todo - return optimized version instead
    }
}
