package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalClass;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.contexts.ClassContext;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveClassInheritanceTask;


public class MgResolveClassDefinitionTask extends MgResolveComponentDefinitionTask<MgClass> {
    @Input
    private final MgLogicalClass logicalClass;

    @Output
    private MgClass clazz;

    public MgResolveClassDefinitionTask(Store<MgClass> store, Context context, MgLogicalClass logicalClass) {
        super(store, new ClassContext(context), logicalClass);
        this.logicalClass = logicalClass;
    }

    @Override
    protected ClassContext getContext() {
        return (ClassContext) super.getContext();
    }

    @Override
    public MgClass getOutput() {
        return clazz;
    }

    @Override
    protected MgClass onResolveComponent() {
        clazz = new MgClass(logicalClass.getName());
        getContext().setClazz(clazz);

        createAndPostpone(
            MgResolveClassInheritanceTask.class,
            logicalClass.getBaseClasses(),
            baseClass -> this.clazz.setBaseClass(baseClass)
        );

        createAndPostponeMore(
            MgResolveMemberVariableDefinitionTask.class,
            logicalClass.getVariables(),
            variables -> {clazz.getVariables().addCollectionLast(variables); clazz.updateCache();}
        );

        createAndPostponeMore(
            MgResolveFunctionDefinitionTask.class,
            logicalClass.getFunctions(),
            functions -> clazz.getFunctions().addCollectionLast(functions)
        );

        createAndPostponeMore(
            MgResolveGlobalVariableDefinitionTask.class,
            logicalClass.getGlobalVariables(),
            variables -> clazz.getGlobalVariables().addCollectionLast(variables)
        );

        return clazz;
    }
}
