package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.components.MgLogicalComponent;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.MgResolveUsagesTask;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.contexts.ComponentContext;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveComponentStampTask;
import cz.mg.language.tasks.mg.resolver.resolvers.MgResolveTask;


public abstract class MgResolveComponentTask<O extends MgObject> extends MgResolveTask<O> {
    @Input
    private final MgLogicalComponent logicalComponent;

    @Subtask
    private MgResolveUsagesTask resolveUsagesTask;

    public MgResolveComponentTask(Store<O> store, Context context, MgLogicalComponent logicalComponent) {
        super(store, new ComponentContext(context));
        this.logicalComponent = logicalComponent;
    }

    public abstract MgComponent getOutput();

    @Override
    protected final O onResolve() {
        resolveUsagesTask = new MgResolveUsagesTask(getContext().getLocation(), logicalComponent.getContext());
        resolveUsagesTask.run();
        ((ComponentContext)getContext()).setComponents(resolveUsagesTask.getComponents());

        createAndPostponeMore(
            MgResolveComponentStampTask.class,
            logicalComponent.getStamps(),
            stamps -> getOutput().setStamps(stamps)
        );

        return onResolveComponent();
    }

    protected abstract O onResolveComponent();
}
