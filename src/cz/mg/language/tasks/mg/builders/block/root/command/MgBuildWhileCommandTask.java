package cz.mg.language.tasks.mg.builders.block.root.command;

import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalBlockCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalWhileCommand;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.part.MgBuildExpressionPartTask;
import cz.mg.language.tasks.mg.builders.pattern.PartProcessor;
import cz.mg.language.tasks.mg.builders.pattern.Processor;


public class MgBuildWhileCommandTask extends MgBuildBlockCommandTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildExpressionPartTask.class,
        MgBuildWhileCommandTask.class,
        (source, destination) -> destination.command = new MgLogicalWhileCommand(source.getExpression())
    );

    @Output
    private MgLogicalWhileCommand command;

    public MgBuildWhileCommandTask(Part part, Block block) {
        super(part, block);
    }

    public MgLogicalWhileCommand getCommand() {
        return command;
    }

    @Override
    protected MgLogicalBlockCommand getOutput() {
        return command;
    }

    @Override
    protected PartProcessor getProcessor() {
        return PROCESSOR;
    }
}
