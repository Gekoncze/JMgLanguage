package cz.mg.language.tasks.mg.builder.block.root;

import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalLunaryOperator;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builder.part.MgBuildOperatorNameTask;
import cz.mg.language.tasks.mg.builder.pattern.PartProcessor;


public class MgBuildLunaryOperatorTask extends MgBuildOperatorTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildOperatorNameTask.class,
        MgBuildLunaryOperatorTask.class,
        (source, destination) -> destination.operator = new MgLogicalLunaryOperator(source.getName())
    );

    @Output
    private MgLogicalLunaryOperator operator;

    public MgBuildLunaryOperatorTask(Block block) {
        super(block);
    }

    @Override
    public MgLogicalLunaryOperator getOperator() {
        return operator;
    }

    @Override
    protected PartProcessor getProcessor() {
        return PROCESSOR;
    }
}
