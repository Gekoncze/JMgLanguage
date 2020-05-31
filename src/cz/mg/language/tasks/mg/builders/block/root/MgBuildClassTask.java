package cz.mg.language.tasks.mg.builders.block.root;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalClass;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameListTask;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;
import static cz.mg.language.tasks.mg.builders.pattern.part.Expectations.*;


public class MgBuildClassTask extends MgBuildBlockTask {
    private static final FieldProcessor NAME_PROCESSOR = new FieldProcessor<>(
            MgBuildNameTask.class,
            MgBuildClassTask.class,
            (source, destination) -> destination.clazz = new MgLogicalClass(source.getName())
    );

    private static final FieldProcessor BASE_CLASS_NAME_PROCESSOR = new FieldProcessor<>(
            MgBuildNameListTask.class,
            MgBuildClassTask.class,
            (source, destination) -> destination.clazz.getBaseClasses().addCollectionLast(source.getList());
    );

    private static final ReadableCollection<PartPattern> PART_PATTERNS = new List<>(
            new PartPattern(KEYWORD("CLASS"), NAME(NAME_PROCESSOR))
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
        return todo;
    }

    @Override
    public ReadableCollection<BlockPattern> getBlockPatterns() {
        return todo;
    }
}
