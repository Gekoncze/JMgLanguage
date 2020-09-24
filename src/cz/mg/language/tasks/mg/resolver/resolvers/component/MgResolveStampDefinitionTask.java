package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalStamp;
import cz.mg.language.entities.mg.runtime.components.MgStamp;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.contexts.StampContext;


public class MgResolveStampDefinitionTask extends MgResolveComponentDefinitionTask {
    @Input
    private final MgLogicalStamp logicalStamp;

    @Output
    private MgStamp stamp;

    public MgResolveStampDefinitionTask(Context context, MgLogicalStamp logicalStamp) {
        super(new StampContext(context), logicalStamp);
        this.logicalStamp = logicalStamp;
    }

    @Override
    protected StampContext getContext() {
        return (StampContext) super.getContext();
    }

    public MgStamp getStamp() {
        return stamp;
    }

    @Override
    protected void onResolveComponent() {
        stamp = new MgStamp(logicalStamp.getName());
        getContext().setStamp(stamp);
    }
}
