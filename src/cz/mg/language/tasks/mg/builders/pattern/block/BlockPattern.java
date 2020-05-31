package cz.mg.language.tasks.mg.builders.pattern.block;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.field.BlockFieldProcessor;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;


public class BlockPattern {
    @Value
    private final Order order;

    @Value
    private final Requirement requirement;

    @Value
    private final Count count;

    @Part
    private final BlockFieldProcessor fieldProcessor;

    @Link
    private final ReadableCollection<PartPattern> partPatterns;

    @Link
    private final ReadableCollection<BlockPattern> blockPatterns;

    public BlockPattern(Order order, Requirement requirement, Count count, BlockFieldProcessor fieldProcessor) {
        this.order = order;
        this.requirement = requirement;
        this.count = count;
        this.fieldProcessor = fieldProcessor;
        try {
            MgBuildBlockTask buildBlockTask = (MgBuildBlockTask) fieldProcessor.getFactory().newInstance((Object)null);
            this.partPatterns = buildBlockTask.getPartPatterns();
            this.blockPatterns = buildBlockTask.getBlockPatterns();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getOrder() {
        return order;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public Count getCount() {
        return count;
    }

    public BlockFieldProcessor getFieldProcessor() {
        return fieldProcessor;
    }

    public ReadableCollection<PartPattern> getPartPatterns() {
        return partPatterns;
    }

    public ReadableCollection<BlockPattern> getBlockPatterns() {
        return blockPatterns;
    }
}
