package cz.mg.language.tasks.mg.resolver.resolvers.link;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.filter.ClassFilter;
import cz.mg.language.tasks.mg.resolver.resolvers.MgResolveTask;


public class MgResolveBaseClassesTask extends MgResolveTask {
    @Input
    private final List<ReadableText> logicalClasses;

    @Output
    private MgClass baseClass;

    public MgResolveBaseClassesTask(Context context, List<ReadableText> logicalBaseClasses) {
        super(context);
        this.logicalClasses = logicalBaseClasses;
    }

    public MgClass getBaseClass() {
        return baseClass;
    }

    @Override
    protected void onRun() {
        if(logicalClasses.count() < 1) return;
        if(logicalClasses.count() > 1) throw new LanguageException("Multiple inheritance is not supported (yet?).");
        ReadableText logicalClass = logicalClasses.getFirst();
        baseClass = new ClassFilter(getContext(), logicalClass).find();
    }
}
