package cz.mg.language.tasks.mg.resolvers;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.architecture.MgLogicalApplication;
import cz.mg.language.entities.mg.logical.components.*;
import cz.mg.language.entities.mg.runtime.architecture.MgApplication;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.components.MgStamp;


public class MgPrepareRuntimeObjectsTask extends MgResolverTask {
    @Input
    private final MgLogicalApplication logicalApplication;

    @Output
    private MgApplication application;

    public MgPrepareRuntimeObjectsTask(MgLogicalApplication logicalApplication) {
        this.logicalApplication = logicalApplication;
    }

    public MgApplication getApplication() {
        return application;
    }

    @Override
    protected void onRun() {
        application = new MgApplication(logicalApplication.getName());
        application.getRoot().getObjects().addCollectionLast(
            prepareLocation(logicalApplication.getRoot()).getObjects()
        );
    }

    private MgLocation prepareLocation(MgLogicalLocation logicalLocation){
        MgLocation location = new MgLocation(logicalLocation.getName());
        for(MgLogicalComponent logicalComponent : logicalLocation.getComponents()){
            location.getObjects().addLast(prepareComponent(logicalComponent));
        }
        return location;
    }

    private MgComponent prepareComponent(MgLogicalComponent logicalComponent){
        if(logicalComponent instanceof MgLogicalLocation){
            return prepareLocation((MgLogicalLocation) logicalComponent);
        }

        if(logicalComponent instanceof MgLogicalStamp){
            return new MgStamp(logicalComponent.getName());
        }

        if(logicalComponent instanceof MgLogicalClass){
            return new MgClass(logicalComponent.getName()); // TODO - might need to prepare typeless variables
        }

        if(logicalComponent instanceof MgLogicalFunction){
            return new MgFunction(logicalComponent.getName()); // TODO - might need to prepare typeless variables
        }

        throw new LanguageException("Unsupported logical component for resolve: " + logicalComponent.getClass().getSimpleName());
    }
}
