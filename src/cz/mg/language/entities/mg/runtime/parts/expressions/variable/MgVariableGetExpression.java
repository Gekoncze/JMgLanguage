package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public interface MgVariableGetExpression {
    public static MgOutputConnector createOutputConnector(MgVariable variable){
        return new MgOutputConnector(variable.getDatatype());
    }
}
