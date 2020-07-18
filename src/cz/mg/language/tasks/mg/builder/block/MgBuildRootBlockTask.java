package cz.mg.language.tasks.mg.builder.block;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.MgLogicalEntity;
import cz.mg.language.entities.mg.logical.components.MgLogicalComponent;
import cz.mg.language.entities.mg.logical.parts.MgLogicalContext;
import cz.mg.language.entities.mg.logical.parts.MgLogicalUsage;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builder.block.root.MgBuildClassTask;
import cz.mg.language.tasks.mg.builder.block.root.MgBuildFunctionTask;
import cz.mg.language.tasks.mg.builder.block.root.MgBuildUsageTask;
import cz.mg.language.tasks.mg.builder.pattern.*;


public class MgBuildRootBlockTask extends MgBuildBlockTask {
    private static final ReadableCollection<Pattern> PATTERNS = new List<>(
        // build usages
        new Pattern(
            Order.STRICT,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildUsageTask.class,
                MgBuildRootBlockTask.class,
                (source, destination) -> destination.entities.addLast(source.getUsage())
            ),
            "USING"
        ),

        // build classes
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildClassTask.class,
                MgBuildRootBlockTask.class,
                (source, destination) -> destination.entities.addLast(source.getClazz())
            ),
            "CLASS"
        ),

        // build functions
        new Pattern(
            Order.RANDOM,
            Requirement.OPTIONAL,
            Count.MULTIPLE,
            new BlockProcessor<>(
                MgBuildFunctionTask.class,
                MgBuildRootBlockTask.class,
                (source, destination) -> destination.entities.addLast(source.getFunction())
            ),
            "FUNCTION"
        )
    );

    @cz.mg.language.annotations.entity.Part
    private final List<MgLogicalEntity> entities = new List<>();

    @Input
    private final ReadableText contextName;

    @Output
    private MgLogicalComponent component;

    public MgBuildRootBlockTask(Block block) {
        super(null, block);
        contextName = null;
    }

    public MgBuildRootBlockTask(Block block, ReadableText contextName) {
        super(null, block);
        this.contextName = contextName;
    }

    @Override
    protected void onRun() {
        super.onRun();

        MgLogicalContext context = new MgLogicalContext(contextName);
        for(MgLogicalEntity entity : entities){
            if(entity instanceof MgLogicalUsage){
                context.getUsages().addLast((MgLogicalUsage) entity);
            } else if(entity instanceof MgLogicalComponent){
                if(component == null){
                    component = (MgLogicalComponent) entity;
                } else {
                    throw new LanguageException("Expected only one component.");
                }
            } else {
                throw new LanguageException("Unexpected entity " + entity.getClass().getSimpleName() + ".");
            }
        }

        if(component != null){
            component.setContext(context);
        } else {
            throw new LanguageException("Missing component.");
        }
    }

    public MgLogicalComponent getComponent() {
        return component;
    }

    @Override
    protected Object getOutput() {
        return entities;
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
    protected void buildPart(Part part){
        // nothing to do
    }
}
