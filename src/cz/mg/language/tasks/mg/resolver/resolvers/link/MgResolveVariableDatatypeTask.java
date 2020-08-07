package cz.mg.language.tasks.mg.resolver.resolvers.link;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.filter.ClassFilter;
import cz.mg.language.tasks.mg.resolver.resolvers.MgResolveTask;


public class MgResolveVariableDatatypeTask extends MgResolveTask<MgDatatype> {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgDatatype datatype;

    public MgResolveVariableDatatypeTask(Store<MgDatatype> store, Context context, MgLogicalVariable logicalVariable) {
        super(store, context);
        this.logicalVariable = logicalVariable;
    }

    public MgDatatype getDatatype() {
        return datatype;
    }

    @Override
    protected MgDatatype onResolve() {
        ClassFilter<MgType> filter = new ClassFilter<>(getContext(), logicalVariable.getType(), MgType.class);
        this.datatype = new MgDatatype(
            filter.find(),
            MgDatatype.Storage.valueOf(logicalVariable.getStorage().name()),
            MgDatatype.Requirement.valueOf(logicalVariable.getRequirement().name())
        );
        return datatype;
    }
}
