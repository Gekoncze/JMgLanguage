package cz.mg.language.tasks.mg.builders.block;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.MgLogicalEntity;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.root.MgBuildUsageTask;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.pattern.block.*;
import cz.mg.language.tasks.mg.builders.pattern.token.TokenPattern;


public class MgBuildRootTask extends MgBuildBlockTask {
    private static final FieldProcessor USAGE_FIELD_PROCESSOR = new FieldProcessor<>(
            MgBuildUsageTask.class,
            MgBuildRootTask.class,
            (source, destination) -> destination.entities.addLast(source.getUsage())
    );

    private static final ReadableCollection<TokenPattern> TOKEN_PATTERNS = new List<>();

    private static final ReadableCollection<BlockPattern> BLOCK_PATTERNS = new List<>(
            new BlockPattern(Order.STRICT, Requirement.OPTIONAL, Count.MULTIPLE, USAGE_FIELD_PROCESSOR)
    );

    @Output
    private final List<MgLogicalEntity> entities = new List<>();

    public MgBuildRootTask(Block block) {
        super(block);
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
