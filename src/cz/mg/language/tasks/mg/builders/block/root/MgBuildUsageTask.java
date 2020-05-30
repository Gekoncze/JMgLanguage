package cz.mg.language.tasks.mg.builders.block.root;

import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgUsageL;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.block.root.usage.MgBuildAsTask;
import cz.mg.language.tasks.mg.builders.child.*;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.part.MgBuildPathTask;
import cz.mg.language.tasks.mg.builders.pattern.Pattern;
import cz.mg.language.tasks.mg.builders.pattern.Patterns;
import static cz.mg.language.tasks.mg.builders.pattern.Expectations.*;


public class MgBuildUsageTask extends MgBuildBlockTask {
    private static final FieldProcessor pathProcessor = new FieldProcessor<>(
            MgBuildPathTask.class,
            MgBuildUsageTask.class,
            (source, destination) -> destination.usage.setPath((source.getPath()))
    );

    private static final FieldProcessor aliasProcessor = new FieldProcessor<>(
            MgBuildAsTask.class,
            MgBuildUsageTask.class,
            (source, destination) -> destination.usage.setAlias(source.getAlias())
    );

    private static final Patterns PATTERNS = new Patterns(
            new Pattern(KEYWORD("USING"), NAME(pathProcessor)),
            new Pattern(KEYWORD("USING"), PATH(pathProcessor))
    );

    private static final Children CHILDREN = new Children(
            new Child(Order.STRICT, Requirement.MANDATORY, Count.SINGLE, aliasProcessor)
    );

    @Output
    private MgUsageL usage = new MgUsageL();

    public MgBuildUsageTask(Block block) {
        super(block);
    }

    public MgUsageL getUsage() {
        return usage;
    }

    @Override
    public Patterns getPatterns() {
        return PATTERNS;
    }

    @Override
    public Children getChildren() {
        return CHILDREN;
    }
}
