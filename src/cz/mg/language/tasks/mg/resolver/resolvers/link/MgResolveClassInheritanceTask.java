package cz.mg.language.tasks.mg.resolver.resolvers.link;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.Filter;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.resolvers.MgResolveTask;


public class MgResolveClassInheritanceTask extends MgResolveTask<MgClass> {
    @Input
    private final ReadableText logicalClass;

    @Output
    private MgClass clazz;

    public MgResolveClassInheritanceTask(Store<MgClass> store, Context context, ReadableText logicalClass) {
        super(store, context);
        this.logicalClass = logicalClass;
    }

    @Override
    protected MgClass onResolve() {
        return new Filter<>(getContext(), MgClass.class, logicalClass).find();
    }
}
