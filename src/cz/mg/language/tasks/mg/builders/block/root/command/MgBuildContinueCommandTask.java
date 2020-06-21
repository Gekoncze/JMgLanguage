package cz.mg.language.tasks.mg.builders.block.root.command;

import cz.mg.collections.Clump;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalContinueCommand;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builders.pattern.Pattern;
import cz.mg.language.tasks.mg.builders.pattern.Processor;


public class MgBuildContinueCommandTask extends MgBuildCommandTask {
    private static final Processor PROCESSOR = new Processor<>(
        MgBuildNameTask.class,
        MgBuildContinueCommandTask.class,
        (source, destination) -> destination.command = new MgLogicalContinueCommand(source.getName())
    );

    @Output
    private MgLogicalContinueCommand command;

    public MgBuildContinueCommandTask(Part part, Block block) {
        super(part, block);
    }

    public MgLogicalContinueCommand getCommand() {
        return command;
    }

    @Override
    protected Object getOutput() {
        return command;
    }

    @Override
    protected Processor getProcessor() {
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
            command = new MgLogicalContinueCommand();
        }
    }
}
