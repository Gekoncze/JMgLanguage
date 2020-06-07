package cz.mg.language.tasks.mg.builders.block.part;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.part.multiple.MgBuildMultiplePartTask;
import cz.mg.language.tasks.mg.builders.part.single.MgBuildSinglePartTask;
import cz.mg.language.tasks.mg.builders.pattern.block.BlockPattern;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;

import static cz.mg.language.tasks.mg.builders.pattern.part.Expectations.LIST;
import static cz.mg.language.tasks.mg.builders.pattern.part.Expectations.PART;

todo; // todo - check for possible errors
public abstract class MgBuildPartBlockTask<Output> extends MgBuildBlockTask {
    @cz.mg.language.annotations.task.Output
    private List<Output> output;

    public MgBuildPartBlockTask(Block block) {
        super(block);
    }

    public List<Output> getOutput() {
        return output;
    }

    @Override
    public ReadableCollection<PartPattern> getPartPatterns() {
        if(getPartPatternsCache() == null){
            setPartPatternsCache(new List<>(
                    new PartPattern(PART(new PartFieldProcessor<>(
                            getBuildSinglePartTask(),
                            MgBuildPartBlockTask.class,
                            (source, destination) -> destination.output.addLast(source.getOutput())
                    ))),
                    new PartPattern(LIST(new PartFieldProcessor<>(
                            getBuildMultiplePartTask(),
                            MgBuildPartBlockTask.class,
                            (source, destination) -> destination.output.addCollectionLast(source.getOutput())
                    )))
            ));
        }

        return getPartPatternsCache();
    }

    @Override
    public ReadableCollection<BlockPattern> getBlockPatterns() {
        return null;
    }

    protected abstract List<PartPattern> getPartPatternsCache();
    protected abstract void setPartPatternsCache(List<PartPattern> partPatternsCache);
    protected abstract Class<? extends MgBuildSinglePartTask<Output>> getBuildSinglePartTask();
    protected abstract Class<? extends MgBuildMultiplePartTask<Output>> getBuildMultiplePartTask();
}
