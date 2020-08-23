package cz.mg.language.tasks.mg.builder.block.root.command;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCheckpointCommand;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.tasks.mg.builder.pattern.*;


public class MgBuildCheckpointCommandTask extends MgBuildCommandTask {
    private static final List<Pattern> PATTERNS = new List<>(
        // build try command
        new Pattern(
            Order.STRICT,
            Requirement.MANDATORY,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildTryCommandTask.class,
                MgBuildCheckpointCommandTask.class,
                (source, destination) -> destination.command.getCommands().addLast(source.getCommand())
            ),
            "TRY"
        ),

        // build catch command
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildCatchCommandTask.class,
                MgBuildCheckpointCommandTask.class,
                (source, destination) -> destination.command.getCommands().addLast(source.getCommand())
            ),
            "CATCH"
        ),

        // build finally command
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildFinallyCommandTask.class,
                MgBuildCheckpointCommandTask.class,
                (source, destination) -> destination.command.getCommands().addLast(source.getCommand())
            ),
            "FINALLY"
        )
    );

    @Output
    private MgLogicalCheckpointCommand command;

    public MgBuildCheckpointCommandTask(Block block) {
        super(block);
    }

    public MgLogicalCheckpointCommand getCommand() {
        return command;
    }

    @Override
    protected Object getOutput() {
        return command;
    }

    @Override
    protected PartProcessor getProcessor() {
        return null;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    protected void buildParts(List<Part> parts) {
        command = new MgLogicalCheckpointCommand();
    }
}
