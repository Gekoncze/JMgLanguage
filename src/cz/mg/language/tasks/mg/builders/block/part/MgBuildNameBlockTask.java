package cz.mg.language.tasks.mg.builders.block.part;

import cz.mg.collections.Clump;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builders.pattern.Pattern;
import cz.mg.language.tasks.mg.builders.pattern.Processor;


public class MgBuildNameBlockTask extends MgBuildBlockTask {
    private static final Processor PROCESSOR = new Processor<>(
        MgBuildNameTask.class,
        MgBuildNameBlockTask.class,
        (source, destination) -> destination.name = source.getName()
    );

    @Output
    private ReadableText name;

    public MgBuildNameBlockTask(Part part, Block block) {
        super(part, block);
    }

    public ReadableText getName() {
        return name;
    }

    @Override
    protected Object getOutput() {
        return name;
    }

    @Override
    protected Processor getProcessor() {
        return PROCESSOR;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        return null;
    }
}
