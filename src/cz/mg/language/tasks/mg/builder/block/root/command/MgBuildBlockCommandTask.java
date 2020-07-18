package cz.mg.language.tasks.mg.builder.block.root.command;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalBlockCommand;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builder.block.part.MgBuildNameBlockTask;
import cz.mg.language.tasks.mg.builder.pattern.*;


public abstract class MgBuildBlockCommandTask extends MgBuildBlockTask {
    private static final List<Pattern> PATTERNS = new List<>(
        // build alias
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildNameBlockTask.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().setName(source.getName())
            ),
            "AS"
        ),

        // build expression command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildExpressionCommand.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().getCommands().addLast(source.getCommand())
            )
        ),

        // build if command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildIfCommandTask.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().getCommands().addLast(source.getCommand())
            ),
            "IF"
        ),

        // build else if command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildElseIfCommandTask.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().getCommands().addLast(source.getCommand())
            ),
            "ELSE", "IF"
        ),

        // build else command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildElseCommandTask.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().getCommands().addLast(source.getCommand())
            ),
            "ELSE"
        ),

        // build while command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildWhileCommandTask.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().getCommands().addLast(source.getCommand())
            ),
            "WHILE"
        ),

        // build return command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildReturnCommandTask.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().getCommands().addLast(source.getCommand())
            ),
            "RETURN"
        ),

        // build continue command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildContinueCommandTask.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().getCommands().addLast(source.getCommand())
            ),
            "CONTINUE"
        ),

        // build break command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildBreakCommandTask.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().getCommands().addLast(source.getCommand())
            ),
            "BREAK"
        )
    );

    public MgBuildBlockCommandTask(Part part, Block block) {
        super(part, block);
    }

    @Override
    protected abstract MgLogicalBlockCommand getOutput();

    @Override
    protected Clump<Pattern> getPatterns() {
        return PATTERNS;
    }
}
