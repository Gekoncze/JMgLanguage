package cz.mg.language.tasks.mg.resolvers;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.runtime.components.MgStamp;


public class MgResolveComponentStampTask extends MgResolveTask<MgStamp> {
    @Input
    private final ReadableText logicalStamp;

    @Output
    private MgStamp stamp;

    public MgResolveComponentStampTask(Store<MgStamp> store, Context context, ReadableText logicalStamp) {
        super(store, context);
        this.logicalStamp = logicalStamp;
    }

    public MgStamp getStamp() {
        return stamp;
    }

    @Override
    protected MgStamp onResolve() {
        return new Filter<>(getContext(), MgStamp.class, logicalStamp).find();
    }
}
