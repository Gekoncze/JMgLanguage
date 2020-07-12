package cz.mg.language.tasks.mg.resolvers;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalStamp;
import cz.mg.language.entities.mg.runtime.components.MgStamp;


public class MgResolveStampTask extends MgResolveTask<MgStamp> {
    @Input
    private final MgLogicalStamp logicalStamp;

    @Output
    private MgStamp stamp;

    public MgResolveStampTask(Store<MgStamp> store, Context context, MgLogicalStamp logicalStamp) {
        super(store, context);
        this.logicalStamp = logicalStamp;
    }

    public MgStamp getStamp() {
        return stamp;
    }

    @Override
    protected MgStamp onResolve() {
        return this.stamp = new MgStamp(logicalStamp.getName());
    }
}
