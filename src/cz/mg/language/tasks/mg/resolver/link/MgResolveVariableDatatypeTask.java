package cz.mg.language.tasks.mg.resolver.link;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgLogicalDatatype;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.MgPostponeResolveTask;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.search.TypeSearch;


public class MgResolveVariableDatatypeTask extends MgPostponeResolveTask {
    @Input
    private final MgLogicalDatatype logicalDatatype;

    @Output
    private MgDatatype datatype;

    public MgResolveVariableDatatypeTask(Context context, MgLogicalDatatype logicalDatatype) {
        super(context);
        this.logicalDatatype = logicalDatatype;
    }

    public MgDatatype getDatatype() {
        return datatype;
    }

    @Override
    protected void onRun() {
        TypeSearch search = new TypeSearch(getContext().getGlobalSource(), logicalDatatype.getName());
        this.datatype = new MgDatatype(
            search.find(),
            MgDatatype.Storage.valueOf(logicalDatatype.getStorage().name()),
            MgDatatype.Requirement.valueOf(logicalDatatype.getRequirement().name())
        );
    }
}
