package cz.mg.language.tasks.builders.mg.root.usage;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.builders.mg.MgBuildBlockTask;
import cz.mg.language.tasks.builders.mg.pattern.Pattern;
import cz.mg.language.tasks.builders.mg.pattern.Patterns;

import static cz.mg.language.tasks.builders.mg.pattern.Expectations.KEYWORD;
import static cz.mg.language.tasks.builders.mg.pattern.Expectations.NAME;


public class MgBuildAsTask extends MgBuildBlockTask {
    private static final Patterns PATTERNS = new Patterns(
            new Pattern(KEYWORD("AS"), NAME())
    );

    @Output
    private ReadableText alias = null;

    public MgBuildAsTask(Block block) {
        super(block);
    }

    public ReadableText getAlias() {
        return alias;
    }

    @Override
    protected Patterns getPatterns() {
        return PATTERNS;
    }
}
