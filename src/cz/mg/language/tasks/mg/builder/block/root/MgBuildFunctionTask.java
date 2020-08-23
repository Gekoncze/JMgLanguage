package cz.mg.language.tasks.mg.builder.block.root;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builder.block.part.*;
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

        // build binary operator
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildOperatorBlockTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> {
                    if(destination.function.getOperator() == null){
                        destination.function.setOperator(source.getOperator());
                    } else {
                        throw new LanguageException("Multiple operator blocks.");
                    }
                }
            ),
            "OPERATOR"
        ),

        // build unary left operator
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildLeftOperatorBlockTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> {
                    if(destination.function.getOperator() == null){
                        destination.function.setOperator(source.getOperator());
                    } else {
                        throw new LanguageException("Multiple operator blocks.");
                    }
                }
            ),
            "LEFT", "OPERATOR"
        ),

        // build unary right operator
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildRightOperatorBlockTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> {
                    if(destination.function.getOperator() == null){
                        destination.function.setOperator(source.getOperator());
                    } else {
                        throw new LanguageException("Multiple operator blocks.");
                    }
                }
            ),
            "RIGHT", "OPERATOR"
        ),

        // build priority
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildPriorityBlockTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> {
                    if(destination.function.getOperator() != null){
                        destination.function.getOperator().setPriority(source.getPriority());
                    } else {
                        throw new LanguageException("Missing operator to set priority.");
                    }
                }
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

        // build switch command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildSwitchCommandTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getCommands().addLast(source.getCommand())
            ),
            "SWITCH"
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
        ),

        // build rollback command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildRollbackCommandTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getCommands().addLast(source.getCommand())
            ),
            "ROLLBACK"
        ),

        // build checkpoint command
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildCheckpointCommandTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getCommands().addLast(source.getCommand())
            ),
            "CHECKPOINT"
        )
    );

    @Output
    private MgLogicalFunction function;

    public MgBuildFunctionTask(Block block) {
        super(block);
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
