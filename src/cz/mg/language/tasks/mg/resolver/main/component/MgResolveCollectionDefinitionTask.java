package cz.mg.language.tasks.mg.resolver.main.component;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalCollection;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;
import cz.mg.language.entities.mg.runtime.components.types.classes.MgCollection;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.CollectionContext;


public class MgResolveCollectionDefinitionTask extends MgResolveComponentDefinitionTask {
    @Input
    private final MgLogicalCollection logicalCollection;

    @Output
    private MgCollection collection;

    public MgResolveCollectionDefinitionTask(Context context, MgLogicalCollection logicalCollection) {
        super(new CollectionContext(context), logicalCollection);
        this.logicalCollection = logicalCollection;
    }

    @Override
    protected CollectionContext getContext() {
        return (CollectionContext) super.getContext();
    }

    public MgCollection getCollection() {
        return collection;
    }

    @Override
    protected void onResolveComponent(List<MgStamp> stamps) {
        collection = new MgCollection(logicalCollection.getName());
        collection.getStamps().addCollectionLast(stamps);
        getContext().setCollection(collection);

        // TODO
        /*createAndPostponeMore(
            MgResolveCollectionParameterTask.class,
            logicalCollection.getParameters(),
            parameters -> collection.setParameters(parameters)
        );

        createAndPostponeMore(
            MgResolveClassInheritanceTask.class,
            logicalClass.getBaseClasses(),
            classes -> clazz.setClasses(classes)
        );

        createAndPostponeMore(
            MgResolveVariableDefinitionTask.class,
            logicalClass.getVariables(),
            variables -> clazz.setVariables(variables)
        );

        createAndPostponeMore(
            MgResolveFunctionDefinitionTask.class,
            logicalClass.getFunctions(),
            functions -> clazz.setFunctions(functions)
        );*/
    }
}
