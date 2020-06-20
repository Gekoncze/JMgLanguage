package cz.mg.language.tasks.mg.builders.block.root.command;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalBlockCommand;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.block.part.MgBuildNameBlockTask;
import cz.mg.language.tasks.mg.builders.pattern.*;


public abstract class MgBuildBlockCommandTask extends MgBuildBlockTask {
    private static final List<Pattern> PATTERNS = new List<>(
        // build alias
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new Processor<>(
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
            new Processor<>(
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
            new Processor<>(
                MgBuildIfCommandTask.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().getCommands().addLast(source.getCommand())
            )
        ),

        // build else if command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new Processor<>(
                MgBuildElseIfCommandTask.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().getCommands().addLast(source.getCommand())
            )
        ),

        // build else command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new Processor<>(
                MgBuildElseCommandTask.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().getCommands().addLast(source.getCommand())
            )
        ),

        // build while command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new Processor<>(
                MgBuildWhileCommandTask.class,
                MgBuildBlockCommandTask.class,
                (source, destination) -> destination.getOutput().getCommands().addLast(source.getCommand())
            )
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
