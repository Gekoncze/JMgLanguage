package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalClass;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.contexts.ClassContext;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveClassInheritanceTask;


public class MgResolveClassTask extends MgResolveComponentTask<MgClass> {
    @Input
    private final MgLogicalClass logicalClass;

    @Output
    private MgClass clazz;

    public MgResolveClassTask(Store<MgClass> store, Context context, MgLogicalClass logicalClass) {
        super(store, new ClassContext(context), logicalClass);
        this.logicalClass = logicalClass;
    }

    @Override
    public MgClass getOutput() {
        return clazz;
    }

    @Override
    protected MgClass onResolveComponent() {
        clazz = new MgClass(logicalClass.getName());
        ((ClassContext)getContext()).setClazz(clazz);

        createAndPostponeMore(
            MgResolveClassInheritanceTask.class,
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
