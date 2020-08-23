package cz.mg.language.tasks.mg.builder.block.root.command;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalSwitchCommand;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.tasks.mg.builder.part.MgBuildExpressionPartTask;
import cz.mg.language.tasks.mg.builder.pattern.*;


public class MgBuildSwitchCommandTask extends MgBuildCommandTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildExpressionPartTask.class,
        MgBuildSwitchCommandTask.class,
        (source, destination) -> destination.command = new MgLogicalSwitchCommand(source.getExpression())
    );
    
     private static final List<Pattern> PATTERNS = new List<>(
        // build case command
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildCaseCommandTask.class,
                MgBuildSwitchCommandTask.class,
                (source, destination) -> destination.command.getCommands().addLast(source.getCommand())
            ),
            "CASE"
        )
    );

    @Output
    private MgLogicalSwitchCommand command;

    public MgBuildSwitchCommandTask(Block block) {
        super(block);
    }

    public MgLogicalSwitchCommand getCommand() {
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
        return PATTERNS;
    }

    @Override
    protected void buildParts(List<Part> parts) {
        if(parts != null){
            super.buildParts(parts);
        } else {
            command = new MgLogicalSwitchCommand();
        }
    }
}
