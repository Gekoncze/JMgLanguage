package cz.mg.language.tasks.mg.resolver;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.architecture.MgLogicalApplication;
import cz.mg.language.entities.mg.runtime.architecture.MgApplication;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.command.MgResolveCommandTask;
import cz.mg.language.tasks.mg.resolver.context.ApplicationContext;
import cz.mg.language.tasks.mg.resolver.component.*;
import cz.mg.language.tasks.mg.resolver.link.MgResolveBaseClassesTask;
import cz.mg.language.tasks.mg.resolver.link.MgResolveComponentStampTask;
import cz.mg.language.tasks.mg.resolver.link.MgResolveVariableDatatypeTask;


public class MgResolveApplicationTask extends MgPostponeResolveTask {
    @Input
    private final MgLogicalApplication logicalApplication;

    @Output
    private MgApplication application;

    public MgResolveApplicationTask(Context context, MgLogicalApplication logicalApplication) {
        super(new ApplicationContext(context));
        this.logicalApplication = logicalApplication;
    }

    @Override
    protected ApplicationContext getContext() {
        return (ApplicationContext) super.getContext();
    }

    public MgApplication getApplication() {
        return application;
    }

    @Override
    protected void onRun() {
        application = new MgApplication(logicalApplication.getName());
        getContext().setApplication(application);

        postpone(MgResolveLocationTask.class, () -> {
            MgResolveLocationTask task = new MgResolveLocationTask(getContext(), logicalApplication.getRoot());
            task.run();
            application.getRoot().getComponents().addCollectionLast(task.getLocation().getComponents());
        });

        postpone(MgAddBuildinComponentsTask.class, () -> {
            MgAddBuildinComponentsTask task = new MgAddBuildinComponentsTask(application.getRoot());
            task.run();
        });

        resolvePostponedTasks();
    }

    private void resolvePostponedTasks(){
        resolve(MgResolveLocationTask.class);
        resolve(MgAddBuildinComponentsTask.class);

        resolve(MgResolveStampDefinitionTask.class);
        resolve(MgResolveClassDefinitionTask.class);
        resolve(MgResolveCollectionDefinitionTask.class);
        resolve(MgResolveFunctionVariableDefinitionTask.class);
        resolve(MgResolveClassVariableDefinitionTask.class);
        resolve(MgResolveClassFunctionDefinitionTask.class);

        resolve(MgResolveUsageTask.class);
        resolve(MgResolveComponentStampTask.class);
        resolve(MgResolveBaseClassesTask.class);
        resolve(MgResolveVariableDatatypeTask.class);
        resolve(MgResolveCommandTask.class);
    }
}
