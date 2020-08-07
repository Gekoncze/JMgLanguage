package cz.mg.language.tasks.mg.builder.block.part;

import cz.mg.collections.Clump;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgLogicalOperator;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builder.part.MgBuildRightOperatorTask;
import cz.mg.language.tasks.mg.builder.pattern.PartProcessor;
import cz.mg.language.tasks.mg.builder.pattern.Pattern;


public class MgBuildRightOperatorBlockTask extends MgBuildBlockTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildRightOperatorTask.class,
        MgBuildRightOperatorBlockTask.class,
        (source, destination) -> destination.operator = source.getOperator()
    );

    @Output
    private MgLogicalOperator operator;

    public MgBuildRightOperatorBlockTask(Block block) {
        super(block);
    }

    public MgLogicalOperator getOperator() {
        return operator;
    }

    @Override
    protected Object getOutput() {
        return operator;
    }

    @Override
    protected PartProcessor getProcessor() {
        return PROCESSOR;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        return null;
    }
}
