package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
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
    protected ReadableList<MgExpression> getExpressions() {
        return new List<>();
    }

    @Override
    protected ReadableList<MgInputConnector> getInputConnectors() {
        return new List<>();
    }

    @Override
    protected ReadableList<MgOutputConnector> getOutputConnectors() {
        return new List<>(outputConnector);
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
