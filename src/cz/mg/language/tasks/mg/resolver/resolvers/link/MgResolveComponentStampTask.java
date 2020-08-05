package cz.mg.language.tasks.mg.resolver.resolvers.link;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.runtime.components.MgStamp;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.filter.ClassFilter;
import cz.mg.language.tasks.mg.resolver.resolvers.MgResolveTask;


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
        return new ClassFilter<>(getContext(), logicalStamp, MgStamp.class).find();
    }
}
