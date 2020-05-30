package cz.mg.language.tasks.mg.builders.block.root;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgUsageL;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.block.root.usage.MgBuildAsTask;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.part.MgBuildPathTask;
import cz.mg.language.tasks.mg.builders.pattern.block.*;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;
import static cz.mg.language.tasks.mg.builders.pattern.part.Expectations.*;


public class MgBuildUsageTask extends MgBuildBlockTask {
    private static final FieldProcessor PATH_PROCESSOR = new FieldProcessor<>(
            MgBuildPathTask.class,
            MgBuildUsageTask.class,
            (source, destination) -> destination.usage.setPath((source.getPath()))
    );

    private static final FieldProcessor ALIAS_PROCESSOR = new FieldProcessor<>(
            MgBuildAsTask.class,
            MgBuildUsageTask.class,
            (source, destination) -> destination.usage.setAlias(source.getAlias())
    );

    private static final ReadableCollection<PartPattern> PART_PATTERNS = new List<>(
            new PartPattern(KEYWORD("USING"), NAME(PATH_PROCESSOR)),
            new PartPattern(KEYWORD("USING"), PATH(PATH_PROCESSOR))
    );

    private static final ReadableCollection<BlockPattern> BLOCK_PATTERNS = new List<>(
            new BlockPattern(Order.STRICT, Requirement.MANDATORY, Count.SINGLE, ALIAS_PROCESSOR)
    );

    @Output
    private MgUsageL usage = new MgUsageL();

    public MgBuildUsageTask(Block block) {
        super(block);
    }

    public MgUsageL getUsage() {
        return usage;
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
