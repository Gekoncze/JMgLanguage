package cz.mg.language.tasks.mg.builders.block.root.command;

import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalBlockCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalIfCommand;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.part.MgBuildExpressionPartTask;
import cz.mg.language.tasks.mg.builders.pattern.PartProcessor;


public class MgBuildIfCommandTask extends MgBuildBlockCommandTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildExpressionPartTask.class,
        MgBuildIfCommandTask.class,
        (source, destination) -> destination.command = new MgLogicalIfCommand(source.getExpression())
    );

    @Output
    private MgLogicalIfCommand command;

    public MgBuildIfCommandTask(Part part, Block block) {
        super(part, block);
    }

    public MgLogicalIfCommand getCommand() {
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
