package cz.mg.language.tasks.mg.resolver.resolvers;

import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;


public abstract class MgResolveTask extends MgResolverTask {
    @Mandatory
    private final @Part Map<@Link Class, @Part List<@Part Runnable>> map = new Map<>();

    @Mandatory @Part
    private final Context context;

    public MgResolveTask(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }

    protected void postpone(Class clazz, Runnable runnable){
        List<Runnable> subtasks = map.get(clazz);
        if(subtasks == null) map.set(clazz, subtasks = new List<>());
        subtasks.addLast(runnable);
    }

    protected void resolve(Class clazz){
        List<Runnable> subtasks = map.get(clazz);
        if(subtasks == null) return;
        for(Runnable subtask : subtasks) subtask.run();
    }
}
