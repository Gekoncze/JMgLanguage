package cz.mg.language.tasks.mg.builders.block;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.MgLogicalEntity;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.root.MgBuildClassTask;
import cz.mg.language.tasks.mg.builders.block.root.MgBuildFunctionTask;
import cz.mg.language.tasks.mg.builders.block.root.MgBuildUsageTask;
import cz.mg.language.tasks.mg.builders.field.BlockFieldProcessor;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.block.Count;
import cz.mg.language.tasks.mg.builders.pattern.block.Order;
import cz.mg.language.tasks.mg.builders.pattern.block.Requirement;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;


public class MgBuildRootTask extends MgBuildBlockTask {
    private static final BlockFieldProcessor USAGE_FIELD_PROCESSOR = new BlockFieldProcessor<>(
            MgBuildUsageTask.class,
            MgBuildRootTask.class,
            (source, destination) -> destination.entities.addLast(source.getOutput())
    );

    private static final BlockFieldProcessor CLASS_FIELD_PROCESSOR = new BlockFieldProcessor<>(
            MgBuildClassTask.class,
            MgBuildRootTask.class,
            (source, destination) -> destination.entities.addLast(source.getOutput())
    );

    private static final BlockFieldProcessor FUNCTION_FIELD_PROCESSOR = new BlockFieldProcessor<>(
            MgBuildFunctionTask.class,
            MgBuildRootTask.class,
            (source, destination) -> destination.entities.addLast(source.getOutput())
    );

    private static final ReadableCollection<BlockPattern> BLOCK_PATTERNS = new List<>(
            new BlockPattern(Order.STRICT, Requirement.OPTIONAL, Count.MULTIPLE, USAGE_FIELD_PROCESSOR),
            new BlockPattern(Order.RANDOM, Requirement.OPTIONAL, Count.MULTIPLE, CLASS_FIELD_PROCESSOR),
            new BlockPattern(Order.RANDOM, Requirement.OPTIONAL, Count.MULTIPLE, FUNCTION_FIELD_PROCESSOR)
    );

    @Output
    private final List<MgLogicalEntity> entities = new List<>();

    public MgBuildRootTask(Block block) {
        super(block);
    }

    @Override
    public ReadableCollection<PartPattern> getPartPatterns() {
        return null;
    }

    @Override
    public ReadableCollection<BlockPattern> getBlockPatterns() {
        return BLOCK_PATTERNS;
    }
}
