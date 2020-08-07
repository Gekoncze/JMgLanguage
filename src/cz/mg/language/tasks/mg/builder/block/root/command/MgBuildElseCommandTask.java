package cz.mg.language.tasks.mg.builder.block.root.command;

import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalBlockCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalElseCommand;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builder.pattern.PartProcessor;


public class MgBuildElseCommandTask extends MgBuildBlockCommandTask {
    @Output
    private MgLogicalElseCommand command = new MgLogicalElseCommand();

    public MgBuildElseCommandTask(Block block) {
        super(block);
    }

    public MgLogicalElseCommand getCommand() {
        return command;
    }

    @Override
    protected MgLogicalBlockCommand getOutput() {
        return command;
    }

    @Override
    protected PartProcessor getProcessor() {
        return null;
    }
}
