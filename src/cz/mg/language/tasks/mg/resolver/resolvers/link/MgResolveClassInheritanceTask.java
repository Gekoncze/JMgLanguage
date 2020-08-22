package cz.mg.language.tasks.mg.resolver.resolvers.link;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.filter.ClassFilter;
import cz.mg.language.tasks.mg.resolver.resolvers.MgResolveTask;


public class MgResolveClassInheritanceTask extends MgResolveTask<MgClass> {
    @Input
    private final List<ReadableText> logicalClasses;

    @Output
    private MgClass clazz;

    public MgResolveClassInheritanceTask(Store<MgClass> store, Context context, List<ReadableText> logicalClasses) {
        super(store, context);
        this.logicalClasses = logicalClasses;
    }

    @Override
    protected MgClass onResolve() {
        if(logicalClasses.count() < 1) return null;
        if(logicalClasses.count() > 1) throw new LanguageException("Multiple inheritance is not supported.");
        ReadableText logicalClass = logicalClasses.getFirst();
        return clazz = new ClassFilter<>(getContext(), logicalClass, MgClass.class).find();
    }
}
