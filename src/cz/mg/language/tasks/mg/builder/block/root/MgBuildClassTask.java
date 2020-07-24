package cz.mg.language.tasks.mg.builder.block.root;

import cz.mg.collections.Clump;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalClass;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builder.block.part.MgBuildDeclarationBlockTask;
import cz.mg.language.tasks.mg.builder.block.part.MgBuildNamesBlockTask;
import cz.mg.language.tasks.mg.builder.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builder.pattern.*;


public class MgBuildClassTask extends MgBuildBlockTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildNameTask.class,
        MgBuildClassTask.class,
        (source, destination) -> destination.clazz = new MgLogicalClass(source.getName())
    );

    private static final ReadableCollection<Pattern> PATTERNS = new List<>(
        // build base class names
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildNamesBlockTask.class,
                MgBuildClassTask.class,
                (source, destination) -> destination.clazz.getBaseClasses().addCollectionLast(source.getNames())
            ),
            "IS"
        ),

        // build variables
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildDeclarationBlockTask.class,
                MgBuildClassTask.class,
                (source, destination) -> destination.clazz.getVariables().addLast(source.getDeclaration())
            )
        ),

        // build functions
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildFunctionTask.class,
                MgBuildClassTask.class,
                (source, destination) -> destination.clazz.getFunctions().addLast(source.getFunction())
            ),
            "FUNCTION"
        ),

        // build global variables
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildDeclarationBlockTask.class,
                MgBuildClassTask.class,
                (source, destination) -> destination.clazz.getGlobalVariables().addLast(source.getDeclaration())
            ),
            "GLOBAL"
        )
    );

    @Output
    private MgLogicalClass clazz;

    public MgBuildClassTask(Part part, Block block) {
        super(part, block);
    }

    public MgLogicalClass getClazz() {
        return clazz;
    }

    @Override
    protected Object getOutput() {
        return clazz;
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
