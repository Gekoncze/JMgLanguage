package cz.mg.language.tasks.mg.resolver.resolvers;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.architecture.MgLogicalApplication;
import cz.mg.language.entities.mg.logical.components.*;
import cz.mg.language.entities.mg.runtime.architecture.MgApplication;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.tasks.mg.resolver.MgAddBuildinComponentsTask;
import cz.mg.language.tasks.mg.resolver.MgResolveFunctionCommandsTask;
import cz.mg.language.tasks.mg.resolver.MgResolveUsagesTask;
import cz.mg.language.tasks.mg.resolver.resolvers.component.*;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveClassInheritanceTask;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveComponentStampTask;
import cz.mg.language.tasks.mg.resolver.resolvers.link.MgResolveVariableDatatypeTask;


public class MgResolveApplicationTask extends MgResolveTask<MgApplication> {
    @Input
    private final MgLogicalApplication logicalApplication;

    @Output
    private MgApplication application;

    @Subtask
    private MgAddBuildinComponentsTask addBuildinComponentsTask;

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

        addBuildinComponents();
        prepareStructure();
        resolveStampDefinitions();
        resolveClassDefinitions();
        resolveCollectionDefinitions();
        resolveVariableDefinitions();
        resolveFunctionDefinitions();
        resolveUsages();
        resolveComponentStamps();
        resolveClassInheritance();
        resolveVariableDatatypes();
        resolveCommands();

        return application;
    }

    private void addBuildinComponents(){
        addBuildinComponentsTask = new MgAddBuildinComponentsTask(application.getRoot());
        addBuildinComponentsTask.run();
    }

    private void prepareStructure(){
        application.getRoot().getObjects().addCollectionLast(
            prepareLocation(logicalApplication.getRoot()).getObjects()
        );
    }

    private void resolveStampDefinitions(){
        resolve(MgResolveStampDefinitionTask.class);
    }

    private void resolveClassDefinitions(){
        resolve(MgResolveClassDefinitionTask.class);
    }

    private void resolveCollectionDefinitions(){
        resolve(MgResolveCollectionDefinitionTask.class);
    }

    private void resolveVariableDefinitions(){
        resolve(MgResolveVariableDefinitionTask.class);
    }

    private void resolveFunctionDefinitions(){
        resolve(MgResolveFunctionDefinitionTask.class);
    }

    private void resolveUsages(){
        resolve(MgResolveUsagesTask.class);
    }

    private void resolveComponentStamps(){
        resolve(MgResolveComponentStampTask.class);
    }

    private void resolveClassInheritance(){
        resolve(MgResolveClassInheritanceTask.class);
    }

    private void resolveVariableDatatypes(){
        resolve(MgResolveVariableDatatypeTask.class);
    }

    private void resolveCommands(){
        resolve(MgResolveFunctionCommandsTask.class);
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
                MgResolveStampDefinitionTask.class,
                logicalComponent,
                object -> location.getObjects().addLast(object)
            );
        }

        if(logicalComponent instanceof MgLogicalClass){
            createAndPostpone(
                MgResolveClassDefinitionTask.class,
                logicalComponent,
                object -> location.getObjects().addLast(object)
            );
        }

        if(logicalComponent instanceof MgLogicalFunction){
            createAndPostpone(
                MgResolveFunctionDefinitionTask.class,
                logicalComponent,
                object -> location.getObjects().addLast(object)
            );
        }

        if(logicalComponent instanceof MgLogicalVariable){
            createAndPostpone(
                MgResolveVariableDefinitionTask.class,
                logicalComponent,
                object -> location.getObjects().addLast(object)
            );
        }

        if(logicalComponent instanceof MgLogicalCollection){
            createAndPostpone(
                MgResolveCollectionDefinitionTask.class,
                logicalComponent,
                object -> location.getObjects().addLast(object)
            );
        }

        throw new LanguageException("Resolution of " + logicalComponent.getClass().getSimpleName() + " is not supported in location context.");
    }
}
