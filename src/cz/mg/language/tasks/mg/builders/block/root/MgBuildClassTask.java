package cz.mg.language.tasks.mg.builders.block.root;

import cz.mg.collections.Clump;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalClass;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.block.part.MgBuildNamesBlockTask;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builders.pattern.*;


public class MgBuildClassTask extends MgBuildBlockTask {
    private static final Processor PROCESSOR = new Processor<>(
        MgBuildNameTask.class,
        MgBuildClassTask.class,
        (source, destination) -> destination.clazz = new MgLogicalClass(source.getName())
    );

    private static final ReadableCollection<Pattern> PATTERNS = new List<>(
        // build base class names
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new Processor<>(
                MgBuildNamesBlockTask.class,
                MgBuildClassTask.class,
                (source, destination) -> destination.clazz.getBaseClasses().addCollectionLast(source.getNames())
            ),
            "IS"
        )
    );

    @Output
    private MgLogicalClass clazz;

    public MgBuildClassTask(Part part, Block block) {
        super(part, block);
    }

    public MgLogicalClass getClazz() {
        return clazz;
    }

    @Override
    protected Object getOutput() {
        return clazz;
    }

    @Override
    protected Processor getProcessor() {
        return PROCESSOR;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        return PATTERNS;
    }
}
