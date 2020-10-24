package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgAtomType;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgAtom;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgValueExpression extends MgExpression {
    @Mandatory @Part
    private final MgAtom atom;

    public MgValueExpression(MgAtom atom) {
        super(createInputInterface(), createOutputInterface(atom));
        this.atom = atom;
    }

    public MgObject getAtom() {
        return atom;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        functionInstance.getObjects().set(
            atom.copy(),
            getOutputConnectors().getFirst().getConnection().getConnectionVariable().getOffset()
        );
    }

    private static ReadableArray<MgInputConnector> createInputInterface(){
        return new Array<>(); // no input for values
    }

    private static ReadableArray<MgOutputConnector> createOutputInterface(MgAtom atom) {
        return new Array<>(new MgOutputConnector(createDatatype(atom.getType())));
    }

    private static MgDatatype createDatatype(MgAtomType atomType){
        return new MgDatatype(
            atomType,
            MgDatatype.Storage.DIRECT,
            MgDatatype.Requirement.MANDATORY
        );
    }
}
