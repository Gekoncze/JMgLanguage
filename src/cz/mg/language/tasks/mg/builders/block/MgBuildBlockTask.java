package cz.mg.language.tasks.mg.builders.block;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.collections.map.Map;
import cz.mg.collections.special.FilteredCollection;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.MgBuildTask;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.part.MgBuildPartTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.block.Order;
import cz.mg.language.tasks.mg.builders.pattern.part.Expectation;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;

import java.util.Iterator;


public abstract class MgBuildBlockTask extends MgBuildTask {
    @Input
    private final Block block;

    @Input
    private PartPattern usedPattern = null;

    @Subtask
    private final List<MgBuildTask> subtasks = new List();

    private Map<BlockPattern, Integer> blockPatternUsageCount = new Map<>();
    private List<BlockPattern> blockPatternUsageOrder = new List<>();

    public MgBuildBlockTask(Block block) {
        this.block = block;
    }

    public void setUsedPattern(PartPattern usedPattern) {
        this.usedPattern = usedPattern;
    }

    @Override
    protected final void onRun() {
        buildParts();
        buildBlocks();
    }

    protected void buildParts(){
        for(int i = 0; i < block.getParts().count(); i++){
            Part part = block.getParts().get(i);
            Expectation expectation = usedPattern.getExpectations().get(i);
            if(expectation.getFieldProcessor() != null){
                executePartProcessor(expectation.getFieldProcessor(), part);
            }
        }
    }

    protected void buildBlocks(){
        for(Block childBlock : block.getBlocks()){
            PatternPair pattern = matchPattern(childBlock);
            executeBlockProcessor(pattern.blockPattern.getFieldProcessor(), childBlock, pattern.partPattern);
        }
        validateBlocks();
    }

    private void executePartProcessor(FieldProcessor fieldProcessor, Part part){
        MgBuildPartTask buildPartTask = createBuildPartTask(fieldProcessor, part);
        subtasks.addLast(buildPartTask);
        buildPartTask.run();
        fieldProcessor.getSetter().set(buildPartTask, this);
    }

    private void executeBlockProcessor(FieldProcessor fieldProcessor, Block block, PartPattern usedPattern){
        MgBuildBlockTask buildBlockTask = createBuildBlockTask(fieldProcessor, block);
        subtasks.addLast(buildBlockTask);
        buildBlockTask.setUsedPattern(usedPattern);
        buildBlockTask.run();
        fieldProcessor.getSetter().set(buildBlockTask, this);
    }

    private PatternPair matchPattern(Block childBlock){
        for(BlockPattern blockPattern : getBlockPatterns()){
            for(PartPattern partPattern : blockPattern.getPatterns()){
                if(partPattern.match(childBlock.getParts())){
                    blockPatternUsageCount.set(blockPattern, blockPatternUsageCount.get(blockPattern, 0) + 1);
                    blockPatternUsageOrder.addLast(blockPattern);
                    return new PatternPair(blockPattern, partPattern);
                }
            }
        }
        throw new LanguageException("Could not recognise block.");
    }

    private void validateBlocks(){
        validateOrder();
        validateRequirement();
        validateCount();
    }

    private void validateOrder(){
        FilteredCollection<BlockPattern> expectation = new FilteredCollection<>(
                getBlockPatterns(),
                blockPattern -> blockPattern.getOrder() == Order.STRICT
        );
        FilteredCollection<BlockPattern> reality = new FilteredCollection<>(
                blockPatternUsageOrder,
                blockPattern -> true // blockPattern.getOrder() == Order.STRICT
        );
        Iterator<BlockPattern> expectationIterator = expectation.iterator();
        BlockPattern currentExpectation = expectationIterator.next();
        for(BlockPattern currentReality : reality){
            while(currentReality != currentExpectation){
                if(currentExpectation == null) throw new LanguageException("Illegal block order.");
                currentExpectation = expectationIterator.next();
            }
        }
    }

    private void validateRequirement(){
        for(BlockPattern blockPattern : getBlockPatterns()){
            switch (blockPattern.getRequirement()){
                case MANDATORY:
                    if(blockPatternUsageCount.get(blockPattern, 0) < 0)
                        throw new LanguageException("Missing mandatory block.");
                    break;
                case OPTIONAL:
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    private void validateCount(){
        for(BlockPattern blockPattern : getBlockPatterns()){
            switch (blockPattern.getCount()){
                case SINGLE:
                    if(blockPatternUsageCount.get(blockPattern, 0) > 1)
                        throw new LanguageException("Block specified multiple times.");
                    break;
                case MULTIPLE:
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    private MgBuildPartTask createBuildPartTask(FieldProcessor fieldProcessor, Part part){
        try {
             return (MgBuildPartTask) fieldProcessor.getFactory().newInstance(part);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private MgBuildBlockTask createBuildBlockTask(FieldProcessor fieldProcessor, Block block){
        try {
            return (MgBuildBlockTask) fieldProcessor.getFactory().newInstance(block);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract ReadableCollection<PartPattern> getPartPatterns();
    public abstract ReadableCollection<BlockPattern> getBlockPatterns();

    private static class PatternPair {
        BlockPattern blockPattern;
        PartPattern partPattern;

        public PatternPair(BlockPattern blockPattern, PartPattern partPattern) {
            this.blockPattern = blockPattern;
            this.partPattern = partPattern;
        }
    }
}
