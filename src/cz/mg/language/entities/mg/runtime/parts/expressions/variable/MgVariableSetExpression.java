package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;


public interface MgVariableSetExpression {
    public static MgInputConnector createInputConnector(MgVariable variable){
        return new MgInputConnector(variable.getDatatype());
    }
}
