package cz.mg.language.tasks.mg.builder.block.root.command;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalBlockCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalTryCommand;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.tasks.mg.builder.pattern.PartProcessor;


public class MgBuildTryCommandTask extends MgBuildBlockCommandTask {
    @Output
    private MgLogicalTryCommand command;

    public MgBuildTryCommandTask(Block block) {
        super(block);
    }

    public MgLogicalTryCommand getCommand() {
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

    @Override
    protected void buildParts(List<Part> parts) {
        command = new MgLogicalTryCommand();
    }
}
