package cz.mg.language.tasks.mg.builders.block;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.MgLogicalEntity;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.block.root.MgBuildClassTask;
import cz.mg.language.tasks.mg.builders.block.root.MgBuildFunctionTask;
import cz.mg.language.tasks.mg.builders.block.root.MgBuildUsageTask;
import cz.mg.language.tasks.mg.builders.pattern.*;


public class MgBuildRootTask extends MgBuildBlockTask {
    private static final ReadableCollection<Pattern> PATTERNS = new List<>(
        // build usages
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new Processor<>(
                MgBuildUsageTask.class,
                MgBuildRootTask.class,
                (source, destination) -> destination.entities.addLast(source.getUsage())
            )
        ),

        // build classes
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new Processor<>(
                MgBuildClassTask.class,
                MgBuildRootTask.class,
                (source, destination) -> destination.entities.addLast(source.getClazz())
            )
        ),

        // build functions
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new Processor<>(
                MgBuildFunctionTask.class,
                MgBuildRootTask.class,
                (source, destination) -> destination.entities.addLast(source.getFunction())
            )
        )
    );

    @Output
    private final List<MgLogicalEntity> entities = new List<>();

    public MgBuildRootTask(Block block) {
        super(null, block);
    }

    public List<MgLogicalEntity> getEntities() {
        return entities;
    }

    @Override
    protected Object getOutput() {
        return entities;
    }

    @Override
    public ReadableCollection<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    public Processor getProcessor() {
        return null;
    }

    @Override
    protected void buildPart(Part part){
        // nothing to do
    }
}
