package cz.mg.language.tasks.mg.builders.block.root.clazz.is;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.part.multiple.MgBuildNameListPartTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;
import static cz.mg.language.tasks.mg.builders.pattern.part.Expectations.*;


public class MgBuildNameListBlockTask extends MgBuildBlockTask {
    private static final PartFieldProcessor NAMES_PART_PROCESSOR = new PartFieldProcessor<>(
            MgBuildNameListPartTask.class,
            MgBuildNameListBlockTask.class,
            (source, destination) -> destination.names.addCollectionLast(source.getOutput())
    );

    private static final ReadableCollection<PartPattern> PART_PATTERNS = new List<>(
            new PartPattern(NAME(NAMES_PART_PROCESSOR)),
            new PartPattern(LIST(NAMES_PART_PROCESSOR))
    );

    @Output
    private final List<ReadableText> names = new List<>();

    public MgBuildNameListBlockTask(Block block) {
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
        return null;
    }
}
