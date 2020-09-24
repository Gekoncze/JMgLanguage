package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.components.MgLogicalComponent;
import cz.mg.language.entities.mg.logical.parts.MgLogicalUsage;
import cz.mg.language.tasks.mg.resolver.MgResolveUsageTask;
import cz.mg.language.tasks.mg.resolver.contexts.ComponentContext;
import cz.mg.language.tasks.mg.resolver.resolvers.MgResolveTask;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveComponentStampTask;


public abstract class MgResolveComponentDefinitionTask extends MgResolveTask {
    @Input
    private final MgLogicalComponent logicalComponent;

    public MgResolveComponentDefinitionTask(ComponentContext context, MgLogicalComponent logicalComponent) {
        super(context);
        this.logicalComponent = logicalComponent;
    }

    @Override
    protected ComponentContext getContext() {
        return (ComponentContext) super.getContext();
    }

    @Override
    protected final void onRun() {
        resolveUsages();
        resolveStamps();
        onResolveComponent();
    }

    private void resolveUsages(){
        for(MgLogicalUsage logicalUsage : logicalComponent.getContext().getUsages()){
            postpone(MgResolveUsageTask.class, () -> {
                MgResolveUsageTask task = new MgResolveUsageTask(getContext(), logicalUsage);
                task.run();
                getContext().getUsages().addLast(task.getUsage());
            });
        }
    }

    private void resolveStamps(){
        for(ReadableText logicalStamp : logicalComponent.getStamps()){
            postpone(MgResolveComponentStampTask.class, () -> {
                MgResolveComponentStampTask task = new MgResolveComponentStampTask(getContext(), logicalStamp);
                task.run();
                getContext().getComponent().getStamps().addLast(task.getStamp());
            });
        }
    }

    protected abstract void onResolveComponent();
}
