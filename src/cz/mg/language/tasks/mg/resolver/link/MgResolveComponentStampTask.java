package cz.mg.language.tasks.mg.resolver.link;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.filter.StampFilter;
import cz.mg.language.tasks.mg.resolver.MgPostponeResolveTask;


public class MgResolveComponentStampTask extends MgPostponeResolveTask {
    @Input
    private final ReadableText logicalStamp;

    @Output
    private MgStamp stamp;

    public MgResolveComponentStampTask(Context context, ReadableText logicalStamp) {
        super(context);
        this.logicalStamp = logicalStamp;
    }

    public MgStamp getStamp() {
        return stamp;
    }

    @Override
    protected void onRun() {
        stamp = new StampFilter(getContext(), logicalStamp).find();
    }
}
