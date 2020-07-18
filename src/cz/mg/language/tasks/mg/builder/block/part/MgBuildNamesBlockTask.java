package cz.mg.language.tasks.mg.builder.block.part;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builder.part.group.common.MgBuildNameListTask;
import cz.mg.language.tasks.mg.builder.pattern.*;


public class MgBuildNamesBlockTask extends MgBuildBlockTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildNameListTask.class,
        MgBuildNamesBlockTask.class,
        (source, destination) -> destination.names.addCollectionLast(source.getNames())
    );

    private static final List<Pattern> MANDATORY_PATTERNS = new List<>(
        new Pattern(
            Order.RANDOM,
            Requirement.MANDATORY,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildNameBlockTask.class,
                MgBuildNamesBlockTask.class,
                (source, destination) -> destination.names.addLast(source.getName())
            )
        )
    );

    private static final List<Pattern> OPTIONAL_PATTERNS = new List<>(
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildNameBlockTask.class,
                MgBuildNamesBlockTask.class,
                (source, destination) -> destination.names.addLast(source.getName())
            )
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
    protected PartProcessor getProcessor() {
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
