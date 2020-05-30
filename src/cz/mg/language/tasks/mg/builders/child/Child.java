package cz.mg.language.tasks.mg.builders.child;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.pattern.Patterns;


public class Child {
    @Value
    private final Order order;

    @Value
    private final Requirement requirement;

    @Value
    private final Count count;

    @Part
    private final FieldProcessor fieldProcessor;

    @Link
    private final Patterns patterns;

    @Link
    private final Children children;

    public Child(Order order, Requirement requirement, Count count, FieldProcessor fieldProcessor) {
        this.order = order;
        this.requirement = requirement;
        this.count = count;
        this.fieldProcessor = fieldProcessor;
        try {
            MgBuildBlockTask buildBlockTask = (MgBuildBlockTask) fieldProcessor.getFactory().newInstance((Object)null);
            this.patterns = buildBlockTask.getPatterns();
            this.children = buildBlockTask.getChildren();
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

    public Patterns getPatterns() {
        return patterns;
    }

    public Children getChildren() {
        return children;
    }
}
