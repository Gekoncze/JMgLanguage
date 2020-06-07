package cz.mg.language.tasks.mg.builders.block.root;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalClass;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.block.root.clazz.MgBuildIsTask;
import cz.mg.language.tasks.mg.builders.field.BlockFieldProcessor;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.part.single.MgBuildNamePartTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.block.Count;
import cz.mg.language.tasks.mg.builders.pattern.block.Order;
import cz.mg.language.tasks.mg.builders.pattern.block.Requirement;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;
import static cz.mg.language.tasks.mg.builders.pattern.part.Expectations.*;


public class MgBuildClassTask extends MgBuildBlockTask {
    private static final PartFieldProcessor NAME_PART_PROCESSOR = new PartFieldProcessor<>(
            MgBuildNamePartTask.class,
            MgBuildClassTask.class,
            (source, destination) -> destination.clazz = new MgLogicalClass(source.getOutput())
    );

    private static final BlockFieldProcessor IS_BLOCK_PROCESSOR = new BlockFieldProcessor<>(
            MgBuildIsTask.class,
            MgBuildClassTask.class,
            (source, destination) -> destination.clazz.getBaseClasses().addCollectionLast(source.getNames())
    );

    private static final ReadableCollection<PartPattern> PART_PATTERNS = new List<>(
            new PartPattern(KEYWORD("CLASS"), NAME(NAME_PART_PROCESSOR))
    );

    private static final ReadableCollection<BlockPattern> BLOCK_PATTERNS = new List<>(
            new BlockPattern(Order.STRICT, Requirement.OPTIONAL, Count.SINGLE, IS_BLOCK_PROCESSOR)
    );

    @Output
    private MgLogicalClass clazz = null;

    public MgBuildClassTask(Block block) {
        super(block);
    }

    public MgLogicalClass getClazz() {
        return clazz;
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
