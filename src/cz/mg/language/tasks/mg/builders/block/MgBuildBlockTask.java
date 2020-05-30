package cz.mg.language.tasks.mg.builders.block;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.MgBuildTask;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.part.MgBuildPartTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.part.Expectation;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;


public abstract class MgBuildBlockTask extends MgBuildTask {
    @Input
    private final Block block;

    @Input
    private PartPattern usedPattern = null;

    @Subtask
    private final List<MgBuildTask> subtasks = new List();

    public MgBuildBlockTask(Block block) {
        this.block = block;
    }

    public PartPattern getUsedPattern() {
        return usedPattern;
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
                subtasks.addLast(createPartTask(expectation.getFieldProcessor(), part));
                subtasks.getLast().run();
                expectation.getFieldProcessor().getSetter().set(subtasks.getLast(), this);
            }
        }
    }

    protected void buildBlocks(){
        List<BlockPattern> remainingBlockPatterns = new List<>(getBlockPatterns());
        for(Block childBlock : block.getBlocks()){
            for(BlockPattern pattern : remainingBlockPatterns){
                todo;
            }
        }

        todo;
    }

    private MgBuildPartTask createPartTask(FieldProcessor fieldProcessor, Part part){
        try {
             return (MgBuildPartTask) fieldProcessor.getFactory().newInstance(part);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract ReadableCollection<PartPattern> getPartPatterns();
    public abstract ReadableCollection<BlockPattern> getBlockPatterns();
}
