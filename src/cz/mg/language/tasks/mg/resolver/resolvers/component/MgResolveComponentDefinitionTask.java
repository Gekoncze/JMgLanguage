package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.components.MgLogicalComponent;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.MgResolveUsagesTask;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.contexts.ComponentContext;
import cz.mg.language.tasks.mg.resolver.resolvers.MgResolveTask;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveComponentStampTask;


public abstract class MgResolveComponentDefinitionTask<O extends MgObject> extends MgResolveTask<O> {
    @Input
    private final MgLogicalComponent logicalComponent;

    public MgResolveComponentDefinitionTask(Store<O> store, Context context, MgLogicalComponent logicalComponent) {
        super(store, new ComponentContext(context));
        this.logicalComponent = logicalComponent;
    }

    public abstract MgComponent getOutput();

    @Override
    protected final O onResolve() {
        postpone(
            new MgResolveUsagesTask(
                getContext().getLocation(),
                logicalComponent.getContext(),
                (ComponentContext) getContext()
            )
        );

        createAndPostponeMore(
            MgResolveComponentStampTask.class,
            logicalComponent.getStamps(),
            stamps -> getOutput().setStamps(stamps)
        );

        return onResolveComponent();
    }

    protected abstract O onResolveComponent();
}
