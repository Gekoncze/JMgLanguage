package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgAtom;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgValueExpression extends MgExpression {
    @Mandatory @Part
    private final MgAtom atom;

    @Mandatory @Part
    private final MgOutputConnector outputConnector;

    @Optional @Cache
    private int outputVariableOffset;

    public MgValueExpression(MgAtom atom) {
        this.atom = atom;
        this.outputConnector = createOutputConnector(atom);
    }

    public MgObject getAtom() {
        return atom;
    }

    @Override
    protected ReadableCollection<MgExpression> getExpressions() {
        return new Array<>();
    }

    @Override
    protected ReadableCollection<MgInputConnector> getInputConnectors() {
        return new Array<>();
    }

    @Override
    protected ReadableCollection<MgOutputConnector> getOutputConnectors() {
        return new Array<>(outputConnector);
    }

    @Override
    public void validate() {
        super.validate();
        this.outputVariableOffset = outputConnector
            .getConnection()
            .getConnectionVariable()
            .getCache()
            .getOffset();
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        functionInstance.getObjects().set(atom.copy(), outputVariableOffset);
    }

    private static MgOutputConnector createOutputConnector(MgAtom atom){
        return new MgOutputConnector(new MgDatatype(
            atom.getType(),
            MgDatatype.Storage.ANY,
            MgDatatype.Requirement.MANDATORY
        ));
    }
}
