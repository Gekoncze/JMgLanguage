package cz.mg.language.tasks.mg.resolvers;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.architecture.MgLogicalApplication;
import cz.mg.language.entities.mg.logical.components.*;
import cz.mg.language.entities.mg.runtime.architecture.MgApplication;
import cz.mg.language.entities.mg.runtime.components.MgLocation;


public class MgResolveApplicationTask extends MgResolveTask<MgApplication> {
    @Input
    private final MgLogicalApplication logicalApplication;

    @Output
    private MgApplication application;

    public MgResolveApplicationTask(MgLogicalApplication logicalApplication) {
        super((output)->{}, null);
        this.logicalApplication = logicalApplication;
    }

    public MgApplication getApplication() {
        return application;
    }

    @Override
    protected MgApplication onResolve() {
        application = new MgApplication(logicalApplication.getName());
        application.getRoot().getObjects().addCollectionLast(
            prepareLocation(logicalApplication.getRoot()).getObjects()
        );

        //todo; // TODO - call required resolves in correct order

        return application;
    }

    private MgLocation prepareLocation(MgLogicalLocation logicalLocation){
        MgLocation location = new MgLocation(logicalLocation.getName());
        for(MgLogicalComponent logicalComponent : logicalLocation.getComponents()){
            prepareComponent(logicalComponent, location);
        }
        return location;
    }

    private void prepareComponent(MgLogicalComponent logicalComponent, final MgLocation location){
        if(logicalComponent instanceof MgLogicalLocation){
            location.getObjects().addLast(prepareLocation((MgLogicalLocation) logicalComponent));
        }

        if(logicalComponent instanceof MgLogicalStamp){
            createAndPostpone(
                MgResolveStampTask.class,
                logicalComponent,
                object -> location.getObjects().addLast(object)
            );
        }

        if(logicalComponent instanceof MgLogicalClass){
            createAndPostpone(
                MgResolveClassTask.class,
                logicalComponent,
                object -> location.getObjects().addLast(object)
            );
        }

        if(logicalComponent instanceof MgLogicalFunction){
            createAndPostpone(
                MgResolveFunctionTask.class,
                logicalComponent,
                object -> location.getObjects().addLast(object)
            );
        }

//        if(logicalComponent instanceof MgLogicalVariable){ // TODO - add support if possible
//
//        }

        throw new LanguageException("Unsupported logical component for resolve: " + logicalComponent.getClass().getSimpleName());
    }
}
