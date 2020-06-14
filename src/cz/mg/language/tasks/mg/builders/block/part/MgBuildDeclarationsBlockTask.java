package cz.mg.language.tasks.mg.builders.block.part;

import cz.mg.collections.Clump;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.part.group.common.MgBuildDeclarationListTask;
import cz.mg.language.tasks.mg.builders.pattern.*;


public class MgBuildDeclarationsBlockTask extends MgBuildBlockTask {
    private static final Processor PROCESSOR = new Processor<>(
        MgBuildDeclarationListTask.class,
        MgBuildDeclarationsBlockTask.class,
        (source, destination) -> destination.variables.addCollectionLast(source.getVariables())
    );

    private static final List<Pattern> MANDATORY_PATTERNS = new List<>(
        new Pattern(
            Order.RANDOM,
            Requirement.MANDATORY,
            Count.MULTIPLE,
            PROCESSOR
        )
    );

    private static final List<Pattern> OPTIONAL_PATTERNS = new List<>(
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            PROCESSOR
        )
    );

    @Output
    private final List<MgLogicalVariable> variables = new List<>();

    public MgBuildDeclarationsBlockTask(Part part, Block block) {
        super(part, block);
    }

    public List<MgLogicalVariable> getVariables() {
        return variables;
    }

    @Override
    protected Object getOutput() {
        return variables;
    }

    @Override
    protected Processor getProcessor() {
        return PROCESSOR;
    }

    @Override
    protected Clump<Pattern> getPatterns() {
        if(variables.isEmpty()){
            return MANDATORY_PATTERNS;
        } else {
            return OPTIONAL_PATTERNS;
        }
    }
}
