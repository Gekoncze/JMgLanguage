package cz.mg.language.tasks.mg.builders.block.root.function;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.block.part.MgBuildDeclarationBlockTask;
import cz.mg.language.tasks.mg.builders.field.BlockFieldProcessor;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.part.multiple.MgBuildDeclarationListPartTask;
import cz.mg.language.tasks.mg.builders.part.single.MgBuildDeclarationPartTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.block.Count;
import cz.mg.language.tasks.mg.builders.pattern.block.Order;
import cz.mg.language.tasks.mg.builders.pattern.block.Requirement;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;
import static cz.mg.language.tasks.mg.builders.pattern.part.Expectations.*;


public class MgBuildInputTask extends MgBuildBlockTask {
    private static final PartFieldProcessor DECLARATION_PART_PROCESSOR = new PartFieldProcessor<>(
            MgBuildDeclarationPartTask.class,
            MgBuildInputTask.class,
            (source, destination) -> destination.variables.addLast(source.getOutput())
    );

    private static final PartFieldProcessor DECLARATION_LIST_PART_PROCESSOR = new PartFieldProcessor<>(
            MgBuildDeclarationListPartTask.class,
            MgBuildInputTask.class,
            (source, destination) -> destination.variables.addCollectionLast(source.getOutput())
    );

    private static final BlockFieldProcessor DECLARATION_BLOCK_PROCESSOR = new BlockFieldProcessor<>(
            MgBuildDeclarationBlockTask.class,
            MgBuildInputTask.class,
            (source, destination) -> destination.variables.addCollectionLast(source.getOutput())
    );

    private static final ReadableCollection<PartPattern> PART_PATTERNS = new List<>(
            new PartPattern(KEYWORD("INPUT"), DECLARATION(DECLARATION_PART_PROCESSOR)),
            new PartPattern(KEYWORD("INPUT"), LIST(DECLARATION_LIST_PART_PROCESSOR))
    );

    private static final ReadableCollection<BlockPattern> BLOCK_PATTERNS = new List<>(
            new BlockPattern(Order.RANDOM, Requirement.OPTIONAL, Count.MULTIPLE, DECLARATION_BLOCK_PROCESSOR)
    );

    @Output
    private final List<MgLogicalVariable> variables = new List<>();

    public MgBuildInputTask(Block block) {
        super(block);
    }

    public List<MgLogicalVariable> getVariables() {
        return variables;
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
