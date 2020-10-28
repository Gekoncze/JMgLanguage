package cz.mg.language.tasks.mg.resolver;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.*;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.LocationContext;
import cz.mg.language.tasks.mg.resolver.component.*;


public class MgResolveLocationTask extends MgPostponeResolveTask {
    @Input
    private final MgLogicalLocation logicalLocation;

    @Output
    private MgLocation location;

    protected MgResolveLocationTask(Context context, MgLogicalLocation logicalLocation) {
        super(new LocationContext(context));
        this.logicalLocation = logicalLocation;
    }

    @Override
    protected LocationContext getContext() {
        return (LocationContext) super.getContext();
    }

    public MgLocation getLocation() {
        return location;
    }

    @Override
    protected void onRun() {
        location = new MgLocation(logicalLocation.getName());
        getContext().setLocation(location);

        for(MgLogicalComponent logicalComponent : logicalLocation.getComponents()){
            if(logicalComponent instanceof MgLogicalLocation){
                postpone(MgResolveLocationTask.class, () -> {
                    MgResolveLocationTask task = new MgResolveLocationTask(getContext(), (MgLogicalLocation) logicalComponent);
                    task.run();
                    location.getComponents().addLast(task.getLocation());
                });
            }

            if(logicalComponent instanceof MgLogicalCollection){
                postpone(MgResolveCollectionDefinitionTask.class, () -> {
                    MgResolveCollectionDefinitionTask task = new MgResolveCollectionDefinitionTask(getContext(), (MgLogicalCollection) logicalComponent);
                    task.run();
                    location.getComponents().addLast(task.getCollection());
                });
            }

            if(logicalComponent instanceof MgLogicalClass){
                postpone(MgResolveClassDefinitionTask.class, () -> {
                    MgResolveClassDefinitionTask task = new MgResolveClassDefinitionTask(getContext(), (MgLogicalClass) logicalComponent);
                    task.run();
                    location.getComponents().addLast(task.getClazz());
                });
            }

            if(logicalComponent instanceof MgLogicalFunction){
                postpone(MgResolveClassFunctionDefinitionTask.class, () -> {
                    MgResolveLocationFunctionDefinitionTask task = new MgResolveLocationFunctionDefinitionTask(getContext(), (MgLogicalFunction) logicalComponent);
                    task.run();
                    location.getComponents().addLast(task.getFunction());
                });
            }

            if(logicalComponent instanceof MgLogicalStamp){
                postpone(MgResolveStampDefinitionTask.class, () -> {
                    MgResolveStampDefinitionTask task = new MgResolveStampDefinitionTask(getContext(), (MgLogicalStamp) logicalComponent);
                    task.run();
                    location.getComponents().addLast(task.getStamp());
                });
            }

            if(logicalComponent instanceof MgLogicalVariable){
                postpone(MgResolveLocationVariableDefinitionTask.class, () -> {
                    MgResolveLocationVariableDefinitionTask task = new MgResolveLocationVariableDefinitionTask(getContext(), (MgLogicalVariable) logicalComponent);
                    task.run();
                    location.getComponents().addLast(task.getVariable());
                });
            }

            throw new RuntimeException("Unsupported logical component " + logicalComponent.getClass().getSimpleName() + " for resolve.");
        }
    }
}
