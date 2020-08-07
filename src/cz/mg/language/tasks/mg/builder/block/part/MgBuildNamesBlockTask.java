package cz.mg.language.tasks.mg.builder.block.part;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builder.part.chain.list.MgBuildNameListTask;
import cz.mg.language.tasks.mg.builder.pattern.*;


public class MgBuildNamesBlockTask extends MgBuildBlockTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildNameListTask.class,
        MgBuildNamesBlockTask.class,
        (source, destination) -> destination.names.addCollectionLast(source.getNames())
    );

    private static final List<Pattern> PATTERNS = new List<>(
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

    @Output
    private final List<ReadableText> names = new List<>();

    public MgBuildNamesBlockTask(Block block) {
        super(block);
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
            return PATTERNS;
        } else {
            return null;
        }
    }
}
