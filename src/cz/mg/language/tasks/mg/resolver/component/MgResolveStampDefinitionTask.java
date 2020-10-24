package cz.mg.language.tasks.mg.resolver.component;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalStamp;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.component.StampContext;


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
    protected void onResolveComponent(List<MgStamp> stamps) {
        stamp = new MgStamp(logicalStamp.getName());
        stamp.getStamps().addCollectionLast(stamps);
        getContext().setStamp(stamp);
    }
}
