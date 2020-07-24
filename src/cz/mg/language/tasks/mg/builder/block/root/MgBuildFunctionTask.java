package cz.mg.language.tasks.mg.builder.block.root;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builder.block.part.MgBuildDeclarationsBlockTask;
import cz.mg.language.tasks.mg.builder.block.part.MgBuildOperatorBlockTask;
import cz.mg.language.tasks.mg.builder.block.part.MgBuildPriorityBlockTask;
import cz.mg.language.tasks.mg.builder.block.root.command.*;
import cz.mg.language.tasks.mg.builder.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builder.pattern.*;


public class MgBuildFunctionTask extends MgBuildBlockTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildNameTask.class,
        MgBuildFunctionTask.class,
        (source, destination) -> destination.function = new MgLogicalFunction(source.getName())
    );

    private static final List<Pattern> PATTERNS = new List<>(
        // build input
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildDeclarationsBlockTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getInput().addCollectionLast(source.getVariables())
            ),
            "INPUT"
        ),

        // build output
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildDeclarationsBlockTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getOutput().addCollectionLast(source.getVariables())
            ),
            "OUTPUT"
        ),

        // build operator
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildOperatorBlockTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.setOperator(source.getOperator())
            ),
            "OPERATOR"
        ),

        // build priority
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildPriorityBlockTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.setPriority(source.getPriority())
            ),
            "PRIORITY"
        ),

        // build expression command
        new Pattern(
            Order.RANDOM,
            Requirement.MANDATORY,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildExpressionCommand.class,
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getCommands().addLast(source.getCommand())
            )
        ),

        // build if command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildIfCommandTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getCommands().addLast(source.getCommand())
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
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getCommands().addLast(source.getCommand())
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
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getCommands().addLast(source.getCommand())
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
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getCommands().addLast(source.getCommand())
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
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getCommands().addLast(source.getCommand())
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
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getCommands().addLast(source.getCommand())
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
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getCommands().addLast(source.getCommand())
            ),
            "BREAK"
        )
    );

    @Output
    private MgLogicalFunction function;

    public MgBuildFunctionTask(Part part, Block block) {
        super(part, block);
    }

    public MgLogicalFunction getFunction() {
        return function;
    }

    @Override
    protected Object getOutput() {
        return function;
    }

    @Override
    protected PartProcessor getProcessor() {
        return PROCESSOR;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        return PATTERNS;
    }
}
