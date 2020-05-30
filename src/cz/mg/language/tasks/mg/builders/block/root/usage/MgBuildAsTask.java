package cz.mg.language.tasks.mg.builders.block.root.usage;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.part.MgBuildNameTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.token.Expectations;
import cz.mg.language.tasks.mg.builders.pattern.token.TokenPattern;


public class MgBuildAsTask extends MgBuildBlockTask {
    private static final FieldProcessor nameProcessor = new FieldProcessor<>(
            MgBuildNameTask.class,
            MgBuildAsTask.class,
            (source, destination) -> destination.alias = source.getName()
    );

    private static final ReadableCollection<TokenPattern> TOKEN_PATTERNS = new List<>(
            new TokenPattern(Expectations.KEYWORD("AS"), Expectations.NAME(nameProcessor))
    );

    private static final ReadableCollection<BlockPattern> BLOCK_PATTERNS = new List<>();

    @Output
    private ReadableText alias = null;

    public MgBuildAsTask(Block block) {
        super(block);
    }

    public ReadableText getAlias() {
        return alias;
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
