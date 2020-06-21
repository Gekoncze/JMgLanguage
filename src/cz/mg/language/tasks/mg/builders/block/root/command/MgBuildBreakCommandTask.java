package cz.mg.language.tasks.mg.builders.block.root.command;

import cz.mg.collections.Clump;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalBreakCommand;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builders.pattern.PartProcessor;
import cz.mg.language.tasks.mg.builders.pattern.Pattern;
import cz.mg.language.tasks.mg.builders.pattern.Processor;


public class MgBuildBreakCommandTask extends MgBuildCommandTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildNameTask.class,
        MgBuildBreakCommandTask.class,
        (source, destination) -> destination.command = new MgLogicalBreakCommand(source.getName())
    );

    @Output
    private MgLogicalBreakCommand command;

    public MgBuildBreakCommandTask(Part part, Block block) {
        super(part, block);
    }

    public MgLogicalBreakCommand getCommand() {
        return command;
    }

    @Override
    protected Object getOutput() {
        return command;
    }

    @Override
    protected PartProcessor getProcessor() {
        return PROCESSOR;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        return null;
    }

    @Override
    protected void buildPart(Part part) {
        if(part != null){
            super.buildPart(part);
        } else {
            command = new MgLogicalBreakCommand();
        }
    }
}
