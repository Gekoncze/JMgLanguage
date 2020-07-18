package cz.mg.language.tasks.mg.builder.block.part;

import cz.mg.collections.Clump;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builder.part.MgBuildOperatorTask;
import cz.mg.language.tasks.mg.builder.pattern.PartProcessor;
import cz.mg.language.tasks.mg.builder.pattern.Pattern;


public class MgBuildOperatorBlockTask extends MgBuildBlockTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildOperatorTask.class,
        MgBuildOperatorBlockTask.class,
        (source, destination) -> destination.operator = source.getOperator()
    );

    @Output
    private ReadableText operator;

    public MgBuildOperatorBlockTask(Part part, Block block) {
        super(part, block);
    }

    public ReadableText getOperator() {
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
