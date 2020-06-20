package cz.mg.language.tasks.mg.builders.block.root.command;

import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalBlockCommand;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalElseIfCommand;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.part.MgBuildExpressionPartTask;
import cz.mg.language.tasks.mg.builders.pattern.Processor;


public class MgBuildElseIfCommandTask extends MgBuildBlockCommandTask {
    private static final Processor PROCESSOR = new Processor<>(
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
    protected Processor getProcessor() {
        return PROCESSOR;
    }
}
