package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.other.MgDatatype;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.Filter;
import cz.mg.language.tasks.mg.resolver.Store;


public class MgResolveVariableTask extends MgResolveComponentTask<MgVariable> {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgVariable variable;

    public MgResolveVariableTask(Store<MgVariable> store, Context context, MgLogicalVariable logicalVariable) {
        super(store, context, logicalVariable);
        this.logicalVariable = logicalVariable;
    }

    @Override
    public MgVariable getOutput() {
        return variable;
    }

    @Override
    protected MgVariable onResolveComponent() {
        Filter<MgType> filter = new Filter<>(getContext(), MgType.class, logicalVariable.getType());
        MgDatatype datatype = new MgDatatype(
            filter.find(),
            MgDatatype.Storage.valueOf(logicalVariable.getStorage().name()),
            MgDatatype.Requirement.valueOf(logicalVariable.getRequirement().name())
        );
        return variable = new MgVariable(logicalVariable.getName(), datatype);
    }
}
