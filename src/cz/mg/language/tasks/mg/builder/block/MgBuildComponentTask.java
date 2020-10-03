package cz.mg.language.tasks.mg.builder.block;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalComponent;
import cz.mg.language.entities.mg.logical.parts.MgLogicalContext;
import cz.mg.language.entities.mg.logical.parts.MgLogicalUsage;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.tasks.mg.builder.block.root.MgBuildClassTask;
import cz.mg.language.tasks.mg.builder.block.root.MgBuildFunctionTask;
import cz.mg.language.tasks.mg.builder.block.root.MgBuildUsageTask;
import cz.mg.language.tasks.mg.builder.pattern.*;


public class MgBuildComponentTask extends MgBuildBlockTask {
    private static final ReadableCollection<Pattern> PATTERNS = new List<>(
        // build usages
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildUsageTask.class,
                MgBuildComponentTask.class,
                (source, destination) -> destination.context.getUsages().addLast(source.getUsage()),
                (block, destination) -> new MgBuildUsageTask(block, MgLogicalUsage.Filter.ALL)
            ),
            "USING"
        ),

        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildUsageTask.class,
                MgBuildComponentTask.class,
                (source, destination) -> destination.context.getUsages().addLast(source.getUsage()),
                (block, destination) -> new MgBuildUsageTask(block, MgLogicalUsage.Filter.CLASS)
            ),
            "USING", "CLASS"
        ),

        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildUsageTask.class,
                MgBuildComponentTask.class,
                (source, destination) -> destination.context.getUsages().addLast(source.getUsage()),
                (block, destination) -> new MgBuildUsageTask(block, MgLogicalUsage.Filter.FUNCTION)
            ),
            "USING", "FUNCTION"
        ),

        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildUsageTask.class,
                MgBuildComponentTask.class,
                (source, destination) -> destination.context.getUsages().addLast(source.getUsage()),
                (block, destination) -> new MgBuildUsageTask(block, MgLogicalUsage.Filter.OPERATOR)
            ),
            "USING", "OPERATOR"
        ),

        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildUsageTask.class,
                MgBuildComponentTask.class,
                (source, destination) -> destination.context.getUsages().addLast(source.getUsage()),
                (block, destination) -> new MgBuildUsageTask(block, MgLogicalUsage.Filter.VARIABLE)
            ),
            "USING", "VARIABLE"
        ),

        // build class
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildClassTask.class,
                MgBuildComponentTask.class,
                (source, destination) -> {
                    if(destination.component != null) throw new LanguageException("Expected only one component.");
                    destination.component = source.getClazz();
                }
            ),
            "CLASS"
        ),

        // build function
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.SINGLE,
            new BlockProcessor<>(
                MgBuildFunctionTask.class,
                MgBuildComponentTask.class,
                (source, destination) -> {
                    if(destination.component != null) throw new LanguageException("Expected only one component.");
                    destination.component = source.getFunction();
                }
            ),
            "FUNCTION"
        )
    );

    @Output
    private MgLogicalComponent component;

    @Cache
    private MgLogicalContext context;

    public MgBuildComponentTask(Block block) {
        super(block);
    }

    @Override
    protected void onRun() {
        context = new MgLogicalContext();
        super.onRun();
        if(component == null) throw new LanguageException("Missing component.");
        component.setContext(context);
    }

    public MgLogicalComponent getComponent() {
        return component;
    }

    @Override
    protected Object getOutput() {
        return component;
    }

    @Override
    public ReadableCollection<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    public PartProcessor getProcessor() {
        return null;
    }

    @Override
    protected void buildParts(List<Part> parts){
        // nothing to do
    }
}
