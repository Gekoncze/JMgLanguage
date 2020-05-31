package cz.mg.language.tasks.mg.builders.block.root.clazz;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.block.root.clazz.is.MgBuildNameListBlockTask;
import cz.mg.language.tasks.mg.builders.field.BlockFieldProcessor;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameListPartTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.block.Count;
import cz.mg.language.tasks.mg.builders.pattern.block.Order;
import cz.mg.language.tasks.mg.builders.pattern.block.Requirement;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;
import static cz.mg.language.tasks.mg.builders.pattern.part.Expectations.*;


public class MgBuildIsTask extends MgBuildBlockTask {
    private static final PartFieldProcessor NAMES_PART_PROCESSOR = new PartFieldProcessor<>(
            MgBuildNameListPartTask.class,
            MgBuildIsTask.class,
            (source, destination) -> destination.names.addCollectionLast(source.getNames())
    );

    private static final BlockFieldProcessor NAMES_BLOCK_PROCESSOR = new BlockFieldProcessor<>(
            MgBuildNameListBlockTask.class,
            MgBuildIsTask.class,
            (source, destination) -> destination.names.addCollectionLast(source.getNames())
    );

    private static final ReadableCollection<PartPattern> PART_PATTERNS = new List<>(
            new PartPattern(KEYWORD("IS")),
            new PartPattern(KEYWORD("IS"), NAME(NAMES_PART_PROCESSOR)),
            new PartPattern(KEYWORD("IS"), LIST(NAMES_PART_PROCESSOR))
    );

    private static final ReadableCollection<BlockPattern> BLOCK_PATTERNS = new List<>(
            new BlockPattern(Order.RANDOM, Requirement.OPTIONAL, Count.MULTIPLE, NAMES_BLOCK_PROCESSOR)
    );

    @Output
    private List<ReadableText> names = new List<>();

    public MgBuildIsTask(Block block) {
        super(block);
    }

    public List<ReadableText> getNames() {
        return names;
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
