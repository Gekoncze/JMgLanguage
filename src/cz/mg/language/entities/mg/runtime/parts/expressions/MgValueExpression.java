package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgAtom;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgValueExpression extends MgExpression {
    @Mandatory @Part
    private final MgAtom atom;

    @Mandatory @Part
    private final MgOutputConnector outputConnector;

    public MgValueExpression(MgAtom atom) {
        this.outputConnector = new MgOutputConnector(new MgDatatype(
            atom.getType(),
            MgDatatype.Storage.ANY,
            MgDatatype.Requirement.MANDATORY
        ));
        this.atom = atom;
    }

    public MgObject getAtom() {
        return atom;
    }

    public MgOutputConnector getOutputConnector() {
        return outputConnector;
    }

    @Override
    protected MgCache createCache() {
        return new MgCache(
            new Array<>(),
            new Array<>(getOutputConnector())
        );
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        functionInstance.getObjects().set(
            atom.copy(),
            getOutputConnector().getConnection().getConnectionVariable().getCache().getOffset()
        );
    }
}
