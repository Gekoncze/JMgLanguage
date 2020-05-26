package cz.mg.language.tasks.builders.mg;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.logic.mg.parts.MgUsageL;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.builders.mg.pattern.Pattern;
import cz.mg.language.tasks.builders.mg.pattern.Patterns;
import static cz.mg.language.tasks.builders.mg.pattern.expectations.Expectations.*;


public class MgBuildUsageTask extends MgBuildTask {
    private static final Patterns PATTERNS_USING = new Patterns(
            new Pattern(KEYWORD("USING"), NAME()),
            new Pattern(KEYWORD("USING"), PATH())
    );

    private static final Patterns PATTERNS_AS = new Patterns(
            new Pattern(KEYWORD("AS"), NAME())
    );

    @Input
    private final Block block;

    @Output
    private MgUsageL usage = null;

    @Subtask
    private MgBuildPathTask buildPathTask = null;

    public MgBuildUsageTask(Block block) {
        this.block = block;
    }

    public MgUsageL getUsage() {
        return usage;
    }

    @Override
    protected void onRun() {
        match(PATTERNS_USING, block.getParts());

        usage = new MgUsageL();
        buildPathTask = new MgBuildPathTask(block.getParts().get(1));
        buildPathTask.onRun();
        usage.getPath().addCollectionLast(buildPathTask.getPath());

        for(Block childBlock : block.getBlocks()){
            todo;
        }

        if(block.getBlocks().count() <= 0) throw new LanguageException("Missing as "); todo;
    }
}
