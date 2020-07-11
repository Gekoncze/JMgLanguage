package cz.mg.language.tasks.mg.resolvers;

import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.components.MgLogicalComponent;
import cz.mg.language.entities.mg.logical.components.MgLogicalLocation;
import cz.mg.language.entities.mg.logical.parts.MgLogicalContext;
import cz.mg.language.entities.mg.logical.parts.MgLogicalUsage;


public class MgResolveUsagesTask extends MgResolverTask {
    @Input
    private final MgLogicalContext context;

    @Input
    private final MgLogicalLocation root;

    @Output
    private Map<MgLogicalUsage, MgLogicalComponent> map;

    @Subtask
    private final List<MgResolveUsageTask> resolveUsageTasks = new List<>();

    public MgResolveUsagesTask(MgLogicalContext context, MgLogicalLocation root) {
        this.context = context;
        this.root = root;
    }

    public Map<MgLogicalUsage, MgLogicalComponent> getMap() {
        return map;
    }

    @Override
    protected void onRun() {
        map = new Map<>();
        for(MgLogicalUsage usage : context.getUsages()){
            resolveUsageTasks.addLast(new MgResolveUsageTask(usage, root));
            resolveUsageTasks.getLast().run();
            map.set(usage, resolveUsageTasks.getLast().getTarget());
        }
    }
}
