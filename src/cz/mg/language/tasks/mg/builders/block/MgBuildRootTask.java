package cz.mg.language.tasks.mg.builders.block;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.MgLogicalEntity;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.root.MgBuildUsageTask;
import cz.mg.language.tasks.mg.builders.child.*;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.pattern.Patterns;


public class MgBuildRootTask extends MgBuildBlockTask {
    private static final FieldProcessor USAGE_FIELD_PROCESSOR = new FieldProcessor<>(
            MgBuildUsageTask.class,
            MgBuildRootTask.class,
            (source, destination) -> destination.entities.addLast(source.getUsage())
    );

    private static final Patterns PATTERNS = new Patterns(
    );

    private static final Children CHILDREN = new Children(
            new Child(Order.STRICT, Requirement.OPTIONAL, Count.MULTIPLE, USAGE_FIELD_PROCESSOR)
    );

    @Output
    private final List<MgLogicalEntity> entities = new List<>();

    public MgBuildRootTask(Block block) {
        super(block);
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
