package cz.mg.language.tasks.mg.builders.block.root.function;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.part.MgBuildDeclarationListTask;
import cz.mg.language.tasks.mg.builders.part.MgBuildDeclarationTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;
import static cz.mg.language.tasks.mg.builders.pattern.part.Expectations.*;


public class MgBuildDeclarationBlockTask extends MgBuildBlockTask {
    private static final PartFieldProcessor DECLARATION_PART_PROCESSOR = new PartFieldProcessor<>(
            MgBuildDeclarationTask.class,
            MgBuildDeclarationBlockTask.class,
            (source, destination) -> destination.variables.addLast(source.getVariable())
    );

    private static final PartFieldProcessor DECLARATION_LIST_PART_PROCESSOR = new PartFieldProcessor<>(
            MgBuildDeclarationListTask.class,
            MgBuildDeclarationBlockTask.class,
            (source, destination) -> destination.variables.addCollectionLast(source.getVariables())
    );

    private static final ReadableCollection<PartPattern> PART_PATTERNS = new List<>(
            new PartPattern(DECLARATION(DECLARATION_PART_PROCESSOR)),
            new PartPattern(LIST(DECLARATION_LIST_PART_PROCESSOR))
    );

    @Output
    private final List<MgLogicalVariable> variables = new List<>();

    public MgBuildDeclarationBlockTask(Block block) {
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
        return null;
    }
}
