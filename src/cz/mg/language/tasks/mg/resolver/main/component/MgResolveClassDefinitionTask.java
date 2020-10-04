package cz.mg.language.tasks.mg.resolver.main.component;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalClass;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;
import cz.mg.language.entities.mg.runtime.components.types.classes.MgClass;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.ClassContext;
import cz.mg.language.tasks.mg.resolver.main.link.MgResolveBaseClassesTask;


public class MgResolveClassDefinitionTask extends MgResolveComponentDefinitionTask {
    @Input
    private final MgLogicalClass logicalClass;

    @Output
    private MgClass clazz;

    public MgResolveClassDefinitionTask(Context context, MgLogicalClass logicalClass) {
        super(new ClassContext(context), logicalClass);
        this.logicalClass = logicalClass;
    }

    @Override
    protected ClassContext getContext() {
        return (ClassContext) super.getContext();
    }

    public MgClass getClazz() {
        return clazz;
    }

    @Override
    protected void onResolveComponent(List<MgStamp> stamps) {
        clazz = new MgClass(logicalClass.getName());
        clazz.getStamps().addCollectionLast(stamps);
        getContext().setClazz(clazz);

        postpone(MgResolveBaseClassesTask.class, () -> {
            MgResolveBaseClassesTask task = new MgResolveBaseClassesTask(getContext(), logicalClass.getBaseClasses());
            task.run();
            clazz.setBaseClass(task.getBaseClass());
        });

        for(MgLogicalVariable logicalVariable : logicalClass.getVariables()){
            postpone(MgResolveClassVariableDefinitionTask.class, () -> {
                MgResolveClassVariableDefinitionTask task = new MgResolveClassVariableDefinitionTask(getContext(), logicalVariable);
                task.run();
                clazz.getVariables().addLast(task.getVariable());
                clazz.updateVariableOffsetCache();
            });
        }

        for(MgLogicalFunction logicalFunction : logicalClass.getFunctions()){
            postpone(MgResolveClassFunctionDefinitionTask.class, () -> {
                MgResolveClassFunctionDefinitionTask task = new MgResolveClassFunctionDefinitionTask(getContext(), logicalFunction);
                task.run();
                clazz.getFunctions().addLast(task.getFunction());
            });
        }
    }
}
