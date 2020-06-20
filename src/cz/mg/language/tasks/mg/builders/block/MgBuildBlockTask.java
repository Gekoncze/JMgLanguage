package cz.mg.language.tasks.mg.builders.block;

import cz.mg.collections.Clump;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.MgLogicalEntity;
import cz.mg.language.entities.mg.logical.components.MgLogicalComponent;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.part.MgBuildPartTask;
import cz.mg.language.tasks.mg.builders.MgBuildTask;
import cz.mg.language.tasks.mg.builders.pattern.MgPatternValidatorTask;
import cz.mg.language.tasks.mg.builders.pattern.Pattern;
import cz.mg.language.tasks.mg.builders.pattern.Processor;
import java.util.Iterator;


public abstract class MgBuildBlockTask extends MgBuildTask {
    @Input
    private final Part part;

    @Input
    private final Block block;

    @Subtask
    private MgPatternValidatorTask validatorTask;

    @Subtask
    private final List<MgBuildTask> subtasks = new List<>();

    public MgBuildBlockTask(Part part, Block block) {
        this.part = part;
        this.block = block;
    }

    @Override
    protected final void onRun() {
        buildPart(part);
        buildBlock(block);
        handoverStamps(block.getStamps());
    }

    protected void buildPart(Part part) {
        if(getProcessor() != null){
            if(part != null) {
                MgBuildTask subtask = execute(
                    create(
                        getProcessor().getSourceBuildTaskClass(),
                        part
                    )
                );
                getProcessor().getSetter().set(subtask, this);
            } else {
                throw new LanguageException("Missing part.");
            }
        } else {
            if(part != null){
                throw new LanguageException("Unexpected part.");
            } else {
                // nothing to do
            }
        }
    }

    protected void buildBlock(Block block){
        validatorTask = new MgPatternValidatorTask(getPatterns());

        for(Block childBlock : block.getBlocks()){
            Pattern pattern = match(childBlock);
            validatorTask.register(childBlock, pattern);
            buildChildBlock(childBlock, pattern);
        }

        validatorTask.run();
    }

    protected void buildChildBlock(Block childBlock, Pattern pattern){
        MgBuildTask subtask = execute(
            create(
                pattern.getProcessor().getSourceBuildTaskClass(),
                extractKeyPart(childBlock.getParts()),
                childBlock
            )
        );
        pattern.getProcessor().getSetter().set(subtask, this);
    }

    private MgBuildTask create(Class<? extends MgBuildBlockTask> clazz, Part part, Block block){
        try {
            return clazz.getConstructor(Part.class, Block.class).newInstance(part, block);
        } catch (ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
    }

    private MgBuildTask create(Class<? extends MgBuildPartTask> clazz, Part part){
        try {
            return clazz.getConstructor(Part.class).newInstance(part);
        } catch (ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
    }

    private <T extends MgBuildTask> T execute(T task){
        subtasks.addLast(task);
        task.run();
        return task;
    }

    private Pattern match(Block childBlock){
        if(getPatterns() == null){
            throw new LanguageException("Unexpected child block.");
        }

        for(Pattern pattern : getPatterns()){
            if(match(pattern.getKeywords(), childBlock.getKeywords())){
                return pattern;
            }
        }

        throw new LanguageException("Could not recognise block.");
    }

    private boolean match(
            ReadableCollection<ReadableText> expectedKeywords,
            ReadableCollection<ReadableText> actualKeywords
    ){
        if(expectedKeywords == actualKeywords) return true;
        if(expectedKeywords == null || actualKeywords == null) return false;
        if(expectedKeywords.count() != actualKeywords.count()) return false;
        Iterator<ReadableText> expectedKeywordIterator = expectedKeywords.iterator();
        Iterator<ReadableText> actualKeywordIterator = actualKeywords.iterator();
        while(expectedKeywordIterator.hasNext() && actualKeywordIterator.hasNext()){
            ReadableText expectedKeyword = expectedKeywordIterator.next();
            ReadableText actualKeyword = actualKeywordIterator.next();
            if(!expectedKeyword.equals(actualKeyword)) return false;
        }
        return true;
    }

    private Part extractKeyPart(ReadableCollection<Part> parts){
        if(parts.count() <= 0){
            return null;
        }

        if(parts.count() > 1){
            throw new LanguageException("Orphan part(s).");
        }

        return parts.iterator().next();
    }

    protected void handoverStamps(List<ReadableText> stamps) {
        if(stamps != null){
            if(!stamps.isEmpty()){
                Object output = getOutput();
                if(output instanceof MgLogicalEntity){
                    MgLogicalComponent logicalComponent = (MgLogicalComponent) output;
                    logicalComponent.getStamps().addCollectionLast(stamps);
                } else {
                    throw new LanguageException("Cannot apply stamps to " + output.getClass().getSimpleName() + ".");
                }
            }
        }
    }

    protected abstract Object getOutput();
    protected abstract Processor getProcessor();
    protected abstract Clump<Pattern> getPatterns();
}
