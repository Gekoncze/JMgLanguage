package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgAtom;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgValueExpression extends MgExpression {
    @Mandatory @Part
    private final MgAtom atom;

    public MgValueExpression(MgAtom atom) {
        super(
            new Array<>(),
            new Array<>(createOutputConnector(atom))
        );
        this.atom = atom;
    }

    public MgObject getAtom() {
        return atom;
    }

    @Override
    public void onRun(MgFunctionInstance functionInstance) {
        MgOutputConnector outputConnector = getOutputConnectors().getFirst();
        MgInstanceVariable outputVariable = outputConnector.getConnection().getConnectionVariable();
        MgObject outputObject = atom.copy();

        functionInstance.getObjects().set(outputObject, outputVariable.getCache().getOffset());
    }

    private static MgOutputConnector createOutputConnector(MgAtom atom){
        return new MgOutputConnector(new MgDatatype(
            atom.getType(),
            MgDatatype.Storage.ANY,
            MgDatatype.Requirement.MANDATORY
        ));
    }
}
