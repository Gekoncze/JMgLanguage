package cz.mg.language.tasks.mg.builder.block.part;

import cz.mg.collections.Clump;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builder.part.MgBuildDeclarationTask;
import cz.mg.language.tasks.mg.builder.pattern.PartProcessor;
import cz.mg.language.tasks.mg.builder.pattern.Pattern;


public class MgBuildDeclarationBlockTask extends MgBuildBlockTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildDeclarationTask.class,
        MgBuildDeclarationBlockTask.class,
        (source, destination) -> destination.declaration = source.getVariable()
    );

    @Output
    private MgLogicalVariable declaration;

    public MgBuildDeclarationBlockTask(Block block) {
        super(block);
    }

    public MgLogicalVariable getDeclaration() {
        return declaration;
    }

    @Override
    protected Object getOutput() {
        return declaration;
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
