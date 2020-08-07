package cz.mg.language.tasks.mg.builder.block.part;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builder.part.chain.list.MgBuildDeclarationListTask;
import cz.mg.language.tasks.mg.builder.pattern.*;


public class MgBuildDeclarationsBlockTask extends MgBuildBlockTask {
    private static final PartProcessor PROCESSOR = new PartProcessor<>(
        MgBuildDeclarationListTask.class,
        MgBuildDeclarationsBlockTask.class,
        (source, destination) -> destination.variables.addCollectionLast(source.getVariables())
    );

    private static final List<Pattern> PATTERNS = new List<>(
        new Pattern(
            Order.RANDOM,
            Requirement.MANDATORY,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildDeclarationBlockTask.class,
                MgBuildDeclarationsBlockTask.class,
                (source, destination) -> destination.variables.addLast(source.getDeclaration())
            )
        )
    );

    @Output
    private final List<MgLogicalVariable> variables = new List<>();

    public MgBuildDeclarationsBlockTask(Block block) {
        super(block);
    }

    public List<MgLogicalVariable> getVariables() {
        return variables;
    }

    @Override
    protected Object getOutput() {
        return variables;
    }

    @Override
    protected PartProcessor getProcessor() {
        return PROCESSOR;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        if(variables.isEmpty()){
            return PATTERNS;
        } else {
            return null;
        }
    }
}
