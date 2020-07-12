package cz.mg.language.tasks.mg.resolvers;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalClass;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.tasks.mg.resolvers.contexts.ClassContext;


public class MgResolveClassTask extends MgResolveTask<MgClass> {
    @Input
    private final MgLogicalClass logicalClass;

    @Output
    private MgClass clazz;

    public MgResolveClassTask(Store store, Context context, MgLogicalClass logicalClass) {
        super(store, new ClassContext(context));
        this.logicalClass = logicalClass;
    }

    public MgClass getClazz() {
        return clazz;
    }

    @Override
    protected MgClass onResolve() {
        clazz = new MgClass(logicalClass.getName());
        ((ClassContext)getContext()).setClazz(clazz);

        createAndPostponeMore(
            MgResolveComponentStampTask.class,
            logicalClass.getStamps(),
            stamps -> clazz.setStamps(stamps)
        );

        createAndPostponeMore(
            MgResolveClassInheritance.class,
            logicalClass.getBaseClasses(),
            classes -> clazz.setClasses(classes)
        );

        createAndPostponeMore(
            MgResolveVariableTask.class,
            logicalClass.getVariables(),
            variables -> clazz.setVariables(variables)
        );

        createAndPostponeMore(
            MgResolveFunctionTask.class,
            logicalClass.getFunctions(),
            functions -> clazz.setFunctions(functions)
        );

        return clazz;
    }
}
