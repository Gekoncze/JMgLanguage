package cz.mg.language.tasks.mg.resolver.link;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.runtime.components.types.classes.MgClass;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.MgPostponeResolveTask;
import cz.mg.language.tasks.mg.resolver.search.ClassSearch;


public class MgResolveBaseClassesTask extends MgPostponeResolveTask {
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
        baseClass = new ClassSearch(getContext().getGlobalSource(), logicalClass).find();
    }
}
