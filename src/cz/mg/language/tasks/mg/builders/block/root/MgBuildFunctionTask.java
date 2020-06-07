package cz.mg.language.tasks.mg.builders.block.root;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.block.root.function.MgBuildInputTask;
import cz.mg.language.tasks.mg.builders.block.root.function.MgBuildOutputTask;
import cz.mg.language.tasks.mg.builders.field.BlockFieldProcessor;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.part.single.MgBuildNamePartTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.block.Count;
import cz.mg.language.tasks.mg.builders.pattern.block.Order;
import cz.mg.language.tasks.mg.builders.pattern.block.Requirement;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;
import static cz.mg.language.tasks.mg.builders.pattern.part.Expectations.*;


public class MgBuildFunctionTask extends MgBuildBlockTask {
    private static final PartFieldProcessor NAME_PART_PROCESSOR = new PartFieldProcessor<>(
            MgBuildNamePartTask.class,
            MgBuildFunctionTask.class,
            (source, destination) -> destination.function.setName(source.getOutput())
    );

    private static final BlockFieldProcessor INPUT_BLOCK_PROCESSOR = new BlockFieldProcessor<>(
            MgBuildInputTask.class,
            MgBuildFunctionTask.class,
            (source, destination) -> destination.function.getInput().addCollectionLast(source.getVariables())
    );

    private static final BlockFieldProcessor OUTPUT_BLOCK_PROCESSOR = new BlockFieldProcessor<>(
            MgBuildOutputTask.class,
            MgBuildFunctionTask.class,
            (source, destination) -> destination.function.getOutput().addCollectionLast(source.getVariables())
    );

    private static final ReadableCollection<PartPattern> PART_PATTERNS = new List<>(
            new PartPattern(KEYWORD("FUNCTION"), NAME(NAME_PART_PROCESSOR))
    );

    private static final ReadableCollection<BlockPattern> BLOCK_PATTERNS = new List<>(
            new BlockPattern(Order.STRICT, Requirement.OPTIONAL, Count.SINGLE, INPUT_BLOCK_PROCESSOR),
            new BlockPattern(Order.STRICT, Requirement.OPTIONAL, Count.SINGLE, OUTPUT_BLOCK_PROCESSOR)
    );

    @Output
    private final MgLogicalFunction function = new MgLogicalFunction();

    public MgBuildFunctionTask(Block block) {
        super(block);
    }

    public MgLogicalFunction getFunction() {
        return function;
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
