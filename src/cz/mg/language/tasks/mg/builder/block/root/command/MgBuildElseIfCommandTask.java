package cz.mg.language.tasks.mg.builder.block.root.command;

import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalBlockCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalElseIfCommand;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builder.part.MgBuildExpressionPartTask;
import cz.mg.language.tasks.mg.builder.pattern.PartProcessor;


public class MgBuildElseIfCommandTask extends MgBuildBlockCommandTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildExpressionPartTask.class,
        MgBuildElseIfCommandTask.class,
        (source, destination) -> destination.command = new MgLogicalElseIfCommand(source.getExpression())
    );

    @Output
    private MgLogicalElseIfCommand command;

    public MgBuildElseIfCommandTask(Part part, Block block) {
        super(part, block);
    }

    public MgLogicalElseIfCommand getCommand() {
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
