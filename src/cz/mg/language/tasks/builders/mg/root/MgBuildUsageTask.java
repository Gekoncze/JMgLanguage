package cz.mg.language.tasks.builders.mg.root;

import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.logic.mg.parts.MgUsageL;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.builders.mg.MgBuildBlockTask;
import cz.mg.language.tasks.builders.mg.MgBuildPathTask;
import cz.mg.language.tasks.builders.mg.child.*;
import cz.mg.language.tasks.builders.mg.field.FieldProcessor;
import cz.mg.language.tasks.builders.mg.pattern.Pattern;
import cz.mg.language.tasks.builders.mg.pattern.Patterns;
import cz.mg.language.tasks.builders.mg.root.usage.MgBuildAsTask;

import static cz.mg.language.tasks.builders.mg.pattern.Expectations.*;


public class MgBuildUsageTask extends MgBuildBlockTask {
    private static final FieldProcessor pathNameProcessor = new FieldProcessor(
            MgBuildPathTask.class,
            (source, destination) -> ((MgUsageL)destination).setPath((((MgBuildPathTask)source).getPath()))
    );

    private static final FieldProcessor pathChainProcessor = new FieldProcessor(
            MgBuildPathTask.class,
            (source, destination) -> ((MgUsageL)destination).setPath((((MgBuildPathTask)source).getPath()))
    );

    private static final FieldProcessor aliasProcessor = new FieldProcessor(
            MgBuildAsTask.class,
            (source, destination) -> ((MgUsageL)destination).setAlias(((MgBuildAsTask)source).getAlias())
    );

    private static final Patterns PATTERNS = new Patterns(
            new Pattern(KEYWORD("USING"), NAME(pathNameProcessor)),
            new Pattern(KEYWORD("USING"), PATH(pathChainProcessor))
    );

    private static final Children CHILDREN = new Children(
            new Child(Order.SORTED, Requirement.MANDATORY, Count.SINGLE, aliasProcessor)
    );

    @Output
    private MgUsageL usage = null;

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
