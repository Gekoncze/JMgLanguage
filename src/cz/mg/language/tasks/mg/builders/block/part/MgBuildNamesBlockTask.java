package cz.mg.language.tasks.mg.builders.block.part;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.part.group.common.MgBuildNameListTask;
import cz.mg.language.tasks.mg.builders.pattern.*;


public class MgBuildNamesBlockTask extends MgBuildBlockTask {
    private static final Processor PROCESSOR = new Processor<>(
        MgBuildNameListTask.class,
        MgBuildNamesBlockTask.class,
        (source, destination) -> destination.names.addCollectionLast(source.getNames())
    );

    private static final List<Pattern> MANDATORY_PATTERNS = new List<>(
        new Pattern(
            Order.RANDOM,
            Requirement.MANDATORY,
            Count.MULTIPLE,
            PROCESSOR
        )
    );

    private static final List<Pattern> OPTIONAL_PATTERNS = new List<>(
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            PROCESSOR
        )
    );

    @Output
    private final List<ReadableText> names = new List<>();

    public MgBuildNamesBlockTask(Part part, Block block) {
        super(part, block);
    }

    public List<ReadableText> getNames() {
        return names;
    }

    @Override
    protected Object getOutput() {
        return names;
    }

    @Override
    protected Processor getProcessor() {
        return PROCESSOR;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        if(names.isEmpty()){
            return MANDATORY_PATTERNS;
        } else {
            return OPTIONAL_PATTERNS;
        }
    }
}
