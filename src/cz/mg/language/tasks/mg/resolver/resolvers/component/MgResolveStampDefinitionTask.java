package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalStamp;
import cz.mg.language.entities.mg.runtime.components.MgStamp;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.Store;


public class MgResolveStampDefinitionTask extends MgResolveComponentDefinitionTask<MgStamp> {
    @Input
    private final MgLogicalStamp logicalStamp;

    @Output
    private MgStamp stamp;

    public MgResolveStampDefinitionTask(Store<MgStamp> store, Context context, MgLogicalStamp logicalStamp) {
        super(store, context, logicalStamp);
        this.logicalStamp = logicalStamp;
    }

    @Override
    public MgStamp getOutput() {
        return stamp;
    }

    @Override
    protected MgStamp onResolveComponent() {
        return this.stamp = new MgStamp(logicalStamp.getName());
    }
}
