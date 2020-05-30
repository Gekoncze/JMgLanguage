package cz.mg.language.tasks.mg.builders.block;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.MgBuildTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.token.TokenPattern;


public abstract class MgBuildBlockTask extends MgBuildTask {
    @Input
    private final Block block;

    @Input
    private TokenPattern usedPattern = null;

    @Subtask
    private final List<MgBuildBlockTask> subtasks = new List();

    public MgBuildBlockTask(Block block) {
        this.block = block;
    }

    public TokenPattern getUsedPattern() {
        return usedPattern;
    }

    public void setUsedPattern(TokenPattern usedPattern) {
        this.usedPattern = usedPattern;
    }

    @Override
    protected final void onRun() {
        if(usedPattern != null){
            todo;
        }

        List<Block> remainingBlocks = new List<>(block.getBlocks());
        for(BlockPattern blockPattern : getBlockPatterns()){
            for(TokenPattern tokenPattern : blockPattern.getPatterns()){
                todo;
            }
        }
    }

    //    @Override
//    protected void onRun() {
//        match(PATTERNS, block.getParts());
//
//        usage = new MgUsageL();
//        buildPathTask = new MgBuildPathTask(block.getParts().get(1));
//        buildPathTask.onRun();
//        usage.getPath().addCollectionLast(buildPathTask.getPath());
//
//        for(Block childBlock : block.getBlocks()){
//            todo;
//        }
//
//        if(block.getBlocks().count() <= 0) throw new LanguageException("Missing as "); todo;
//    }

    public abstract ReadableCollection<TokenPattern> getTokenPatterns();
    public abstract ReadableCollection<BlockPattern> getBlockPatterns();
}
