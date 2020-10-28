package cz.mg.language.tasks.mg.builder.block.root;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.logical.components.MgLogicalOperator;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builder.block.part.MgBuildPriorityBlockTask;
import cz.mg.language.tasks.mg.builder.pattern.*;


public abstract class MgBuildOperatorTask extends MgBuildFunctionTask {
    private static final List<Pattern> PATTERNS = new List<>(
        // build priority
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildPriorityBlockTask.class,
                MgBuildOperatorTask.class,
                (source, destination) -> {
                    destination.getOperator().setPriority(source.getPriority());
                }
            ),
            "PRIORITY"
        )
    );

    static {
        PATTERNS.addCollectionFirst(MgBuildFunctionTask.PATTERNS);
    }

    public MgBuildOperatorTask(Block block) {
        super(block);
    }

    public abstract MgLogicalOperator getOperator();

    @Override
    public MgLogicalFunction getFunction() {
        return getOperator();
    }

    @Override
    protected Object getOutput() {
        return getOperator();
    }

    @Override
    protected PartProcessor getProcessor() {
        throw new RuntimeException();
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        return PATTERNS;
    }
}
