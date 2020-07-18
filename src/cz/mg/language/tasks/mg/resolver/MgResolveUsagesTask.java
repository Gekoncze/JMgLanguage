package cz.mg.language.tasks.mg.resolver;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.parts.MgLogicalContext;
import cz.mg.language.entities.mg.logical.parts.MgLogicalUsage;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.tasks.mg.resolver.contexts.ComponentContext;


public class MgResolveUsagesTask extends MgResolverTask {
    @Input
    private final MgLocation location;

    @Input
    private final MgLogicalContext context;

    @Input
    private final ComponentContext componentContext;

    @Subtask
    private final List<MgResolveUsageTask> resolveUsageTasks = new List<>();

    public MgResolveUsagesTask(MgLocation location, MgLogicalContext context, ComponentContext componentContext) {
        this.location = location;
        this.context = context;
        this.componentContext = componentContext;
    }

    @Override
    protected void onRun() {
        List<MgComponent> components = new List<>();
        for(MgLogicalUsage usage : context.getUsages()){
            resolveUsageTasks.addLast(new MgResolveUsageTask(usage, location));
            resolveUsageTasks.getLast().run();
            components.addLast(resolveUsageTasks.getLast().getComponent());
        }
        componentContext.setComponents(components);
    }
}
