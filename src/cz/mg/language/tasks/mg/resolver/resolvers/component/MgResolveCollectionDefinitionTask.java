package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalCollection;
import cz.mg.language.entities.mg.runtime.components.types.MgCollection;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.contexts.CollectionContext;


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
    protected void onResolveComponent() {
        collection = new MgCollection(logicalCollection.getName());
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
