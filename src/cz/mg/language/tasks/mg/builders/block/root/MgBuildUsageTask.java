package cz.mg.language.tasks.mg.builders.block.root;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgUsageL;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.block.root.usage.MgBuildAsTask;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.part.MgBuildPathTask;
import cz.mg.language.tasks.mg.builders.pattern.block.*;
import cz.mg.language.tasks.mg.builders.pattern.token.TokenPattern;
import static cz.mg.language.tasks.mg.builders.pattern.token.Expectations.*;


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

    private static final ReadableCollection<TokenPattern> TOKEN_PATTERNS = new List<>(
            new TokenPattern(KEYWORD("USING"), NAME(pathProcessor)),
            new TokenPattern(KEYWORD("USING"), PATH(pathProcessor))
    );

    private static final ReadableCollection<BlockPattern> BLOCK_PATTERNS = new List<>(
            new BlockPattern(Order.STRICT, Requirement.MANDATORY, Count.SINGLE, aliasProcessor)
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
    public ReadableCollection<TokenPattern> getTokenPatterns() {
        return TOKEN_PATTERNS;
    }

    @Override
    public ReadableCollection<BlockPattern> getBlockPatterns() {
        return BLOCK_PATTERNS;
    }
}
