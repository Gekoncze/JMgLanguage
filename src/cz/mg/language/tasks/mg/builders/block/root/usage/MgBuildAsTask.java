package cz.mg.language.tasks.mg.builders.block.root.usage;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.part.Expectations;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;


public class MgBuildAsTask extends MgBuildBlockTask {
    private static final PartFieldProcessor NAME_PART_PROCESSOR = new PartFieldProcessor<>(
            MgBuildNameTask.class,
            MgBuildAsTask.class,
            (source, destination) -> destination.alias = source.getName()
    );

    private static final ReadableCollection<PartPattern> PART_PATTERNS = new List<>(
            new PartPattern(Expectations.KEYWORD("AS"), Expectations.NAME(NAME_PART_PROCESSOR))
    );

    private static final ReadableCollection<BlockPattern> BLOCK_PATTERNS = new List<>();

    @Output
    private ReadableText alias = null;

    public MgBuildAsTask(Block block) {
        super(block);
    }

    public ReadableText getAlias() {
        return alias;
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
