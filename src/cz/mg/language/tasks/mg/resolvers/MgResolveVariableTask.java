package cz.mg.language.tasks.mg.resolvers;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.other.MgDatatype;


public class MgResolveVariableTask extends MgResolveTask<MgVariable> {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgVariable variable;

    public MgResolveVariableTask(Store<MgVariable> store, Context context, MgLogicalVariable logicalVariable) {
        super(store, context);
        this.logicalVariable = logicalVariable;
    }

    @Override
    protected MgVariable onResolve() {
        Filter<MgType> filter = new Filter<>(getContext(), MgType.class, logicalVariable.getType());
        MgDatatype datatype = new MgDatatype(
            filter.find(),
            MgDatatype.Storage.valueOf(logicalVariable.getStorage().name()),
            MgDatatype.Requirement.valueOf(logicalVariable.getRequirement().name())
        );
        return variable = new MgVariable(logicalVariable.getName(), datatype);
    }
}
