package cz.mg.language.tasks.mg.resolvers;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;


public class MgResolveClassInheritance extends MgResolveTask<MgClass> {
    @Input
    private final ReadableText logicalClass;

    @Output
    private MgClass clazz;

    public MgResolveClassInheritance(Store<MgClass> store, Context context, ReadableText logicalClass) {
        super(store, context);
        this.logicalClass = logicalClass;
    }

    @Override
    protected MgClass onResolve() {
        return new Filter<>(getContext(), MgClass.class, logicalClass).find();
    }
}
