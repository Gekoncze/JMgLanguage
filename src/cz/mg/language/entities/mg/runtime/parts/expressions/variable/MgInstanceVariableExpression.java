package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;


public interface MgInstanceVariableExpression {
    public static MgInputConnector createInputConnector(MgInstanceVariable variable){
        return new MgInputConnector(
            new MgDatatype(
                variable.getParent(),
                MgDatatype.Storage.ANY,
                MgDatatype.Requirement.OPTIONAL
            )
        );
    }
}
