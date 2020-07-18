package cz.mg.language.tasks.mg.builder.block.root;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgLogicalUsage;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builder.block.part.MgBuildNameBlockTask;
import cz.mg.language.tasks.mg.builder.part.group.common.MgBuildNamePathTask;
import cz.mg.language.tasks.mg.builder.pattern.*;


public class MgBuildUsageTask extends MgBuildBlockTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildNamePathTask.class,
        MgBuildUsageTask.class,
        (source, destination) -> destination.usage = new MgLogicalUsage(source.getNames())
    );

    private static final List<Pattern> PATTERNS = new List<>(
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildNameBlockTask.class,
                MgBuildUsageTask.class,
                (source, destination) -> destination.usage.setAlias(source.getName())
            ),
            "AS"
        )
    );

    @Output
    private MgLogicalUsage usage;

    public MgBuildUsageTask(Part part, Block block) {
        super(part, block);
    }

    public MgLogicalUsage getUsage() {
        return usage;
    }

    @Override
    protected Object getOutput() {
        return usage;
    }

    @Override
    protected PartProcessor getProcessor() {
        return PROCESSOR;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        return PATTERNS;
    }
}
