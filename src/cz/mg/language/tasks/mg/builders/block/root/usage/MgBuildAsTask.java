package cz.mg.language.tasks.mg.builders.block.root.usage;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.MgBuildTask;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.child.Children;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.field.FieldSetter;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builders.pattern.Expectations;
import cz.mg.language.tasks.mg.builders.pattern.Pattern;
import cz.mg.language.tasks.mg.builders.pattern.Patterns;


public class MgBuildAsTask extends MgBuildBlockTask {
    private static final FieldProcessor nameProcessor = new FieldProcessor(
            MgBuildNameTask.class,
            new FieldSetter() {
                @Override
                public void set(MgBuildTask source, MgBuildTask destination) {
                    ((MgBuildAsTask)destination).alias = ((MgBuildNameTask)source).getName();
                }
            }
    );

    private static final Patterns PATTERNS = new Patterns(
            new Pattern(Expectations.KEYWORD("AS"), Expectations.NAME(nameProcessor))
    );

    private static final Children CHILDREN = new Children();

    @Output
    private ReadableText alias = null;

    public MgBuildAsTask(Block block) {
        super(block);
    }

    public ReadableText getAlias() {
        return alias;
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
