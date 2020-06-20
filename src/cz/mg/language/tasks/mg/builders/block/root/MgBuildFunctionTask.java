package cz.mg.language.tasks.mg.builders.block.root;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.block.part.MgBuildDeclarationsBlockTask;
import cz.mg.language.tasks.mg.builders.block.root.command.MgBuildExpressionCommand;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builders.pattern.*;


public class MgBuildFunctionTask extends MgBuildBlockTask {
    private static final Processor PROCESSOR = new Processor<>(
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
            new Processor<>(
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
            new Processor<>(
                MgBuildDeclarationsBlockTask.class,
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getOutput().addCollectionLast(source.getVariables())
            ),
            "OUTPUT"
        ),

        // build expression command
        new Pattern(
            Order.RANDOM,
            Requirement.MANDATORY,
            Count.MULTIPLE,
            new Processor<>(
                MgBuildExpressionCommand.class,
                MgBuildFunctionTask.class,
                (source, destination) -> destination.function.getCommands().addLast(source.getCommand())
            )
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
    protected Processor getProcessor() {
        return PROCESSOR;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        return PATTERNS;
    }
}
