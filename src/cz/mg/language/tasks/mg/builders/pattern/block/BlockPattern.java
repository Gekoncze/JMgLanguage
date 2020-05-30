package cz.mg.language.tasks.mg.builders.pattern.block;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.pattern.part.PartPattern;


public class BlockPattern {
    @Value
    private final Order order;

    @Value
    private final Requirement requirement;

    @Value
    private final Count count;

    @Part
    private final FieldProcessor fieldProcessor;

    @Link
    private final ReadableCollection<PartPattern> patterns;

    @Link
    private final ReadableCollection<BlockPattern> children;

    public BlockPattern(Order order, Requirement requirement, Count count, FieldProcessor fieldProcessor) {
        this.order = order;
        this.requirement = requirement;
        this.count = count;
        this.fieldProcessor = fieldProcessor;
        try {
            MgBuildBlockTask buildBlockTask = (MgBuildBlockTask) fieldProcessor.getFactory().newInstance((Object)null);
            this.patterns = buildBlockTask.getPartPatterns();
            this.children = buildBlockTask.getBlockPatterns();
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

    public FieldProcessor getFieldProcessor() {
        return fieldProcessor;
    }

    public ReadableCollection<PartPattern> getPatterns() {
        return patterns;
    }

    public ReadableCollection<BlockPattern> getChildren() {
        return children;
    }
}
