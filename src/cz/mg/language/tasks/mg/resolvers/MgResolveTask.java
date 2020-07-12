package cz.mg.language.tasks.mg.resolvers;

import cz.mg.collections.Collection;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.tasks.Task;


public abstract class MgResolveTask<O extends MgObject> extends Task {
    @Input
    private final Store<O> store;

    @Subtask
    private final List<MgResolveTask> subtasks = new List<>();

    @Part
    private final Context context;

    public MgResolveTask(Store<O> store, Context context) {
        this.store = store;
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }

    @Override
    protected final void onRun() {
        store.put(onResolve());
    }

    protected void resolve(Class<? extends MgResolveTask> clazz){
        for(MgResolveTask subtask : subtasks){
            if(clazz.isInstance(subtask)){
                subtask.run();
            }
            subtask.resolve(clazz);
        }
    }

    protected void postpone(MgResolveTask subtask){
        subtasks.addLast(subtask);
    }

    protected <O2 extends MgObject> void createAndPostpone(
        Class<? extends MgResolveTask<O2>> taskClass,
        Object input,
        Store<O2> store
    ){
        postpone(create(taskClass, store, context, input));
    }

    protected <O2 extends MgObject> void createAndPostponeMore(
        Class<? extends MgResolveTask<O2>> taskClass,
        ReadableCollection inputs,
        ArrayStore<O2> store
    ){
        Array<O2> outputs = new Array<>(inputs.count());
        int i = 0;
        for(Object input : inputs){
            final int ii = i;
            postpone(create(taskClass, (output) -> outputs.set(output, ii), context, input));
            i++;
        }
        store.put(outputs);
    }

    private static <O2 extends MgObject> MgResolveTask<O2> create(Class<? extends MgResolveTask<O2>> clazz, Store<O2> store, Context context, Object input){
        try {
            return clazz.getConstructor(Store.class, Context.class, input.getClass()).newInstance(store, context, input);
        } catch (ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
    }

    protected abstract O onResolve();
}
