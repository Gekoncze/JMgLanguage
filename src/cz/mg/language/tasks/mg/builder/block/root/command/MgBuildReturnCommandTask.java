package cz.mg.language.tasks.mg.builder.block.root.command;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalReturnCommand;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.tasks.mg.builder.part.MgBuildExpressionPartTask;
import cz.mg.language.tasks.mg.builder.pattern.PartProcessor;


public class MgBuildReturnCommandTask extends MgBuildMultilineExpressionTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildExpressionPartTask.class,
        MgBuildReturnCommandTask.class,
        (source, destination) -> destination.command = new MgLogicalReturnCommand(source.getExpression())
    );

    @Output
    private MgLogicalReturnCommand command;

    public MgBuildReturnCommandTask(Block block) {
        super(block);
    }

    public MgLogicalReturnCommand getCommand() {
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
    protected void buildParts(List<Part> parts) {
        if(parts.count() > 0){
            super.buildParts(parts);
        } else {
            command = new MgLogicalReturnCommand();
        }
    }

    @Override
    public MgLogicalExpression getExpression() {
        return command.getExpression();
    }

    @Override
    public void setExpression(MgLogicalExpression expression) {
        command.setExpression(expression);
    }
}
