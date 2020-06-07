package cz.mg.language.tasks.mg.builders.block.root;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgLogicalUsage;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.block.root.usage.MgBuildAsTask;
import cz.mg.language.tasks.mg.builders.field.BlockFieldProcessor;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.part.multiple.MgBuildNamePathTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.block.Count;
import cz.mg.language.tasks.mg.builders.pattern.block.Order;
import cz.mg.language.tasks.mg.builders.pattern.block.Requirement;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;

import static cz.mg.language.tasks.mg.builders.pattern.part.Expectations.*;


public class MgBuildUsageTask extends MgBuildBlockTask {
    private static final PartFieldProcessor PATH_PART_PROCESSOR = new PartFieldProcessor<>(
            MgBuildNamePathTask.class,
            MgBuildUsageTask.class,
            (source, destination) -> destination.output.getPath().addCollectionLast(source.getOutput())
    );

    private static final BlockFieldProcessor AS_BLOCK_PROCESSOR = new BlockFieldProcessor<>(
            MgBuildAsTask.class,
            MgBuildUsageTask.class,
            (source, destination) -> destination.output.setAlias(source.getAlias())
    );

    private static final ReadableCollection<PartPattern> PART_PATTERNS = new List<>(
            new PartPattern(KEYWORD("USING"), NAME(PATH_PART_PROCESSOR)),
            new PartPattern(KEYWORD("USING"), PATH(PATH_PART_PROCESSOR))
    );

    private static final ReadableCollection<BlockPattern> BLOCK_PATTERNS = new List<>(
            new BlockPattern(Order.STRICT, Requirement.MANDATORY, Count.SINGLE, AS_BLOCK_PROCESSOR)
    );

    @Output
    private MgLogicalUsage output = new MgLogicalUsage();

    public MgBuildUsageTask(Block block) {
        super(block);
    }

    public MgLogicalUsage getOutput() {
        return output;
    }

    @Override
    public ReadableCollection<PartPattern> getPartPatterns() {
        return PART_PATTERNS;
    }

    @Override
    public ReadableCollection<BlockPattern> getBlockPatterns() {
        return BLOCK_PATTERNS;
    }
}
