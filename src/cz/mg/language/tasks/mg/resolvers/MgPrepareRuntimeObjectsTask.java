//package cz.mg.language.tasks.mg.resolvers;
//
//import cz.mg.language.annotations.task.Input;
//import cz.mg.language.annotations.task.Output;
//import cz.mg.language.entities.mg.logical.architecture.MgLogicalApplication;
//import cz.mg.language.entities.mg.runtime.architecture.MgApplication;
//
//
//public class MgPrepareRuntimeObjectsTask extends MgResolverTask {
//    @Input
//    private final MgLogicalApplication logicalApplication;
//
//    @Output
//    private MgApplication application;
//
//    public MgPrepareRuntimeObjectsTask(MgLogicalApplication logicalApplication) {
//        this.logicalApplication = logicalApplication;
//    }
//
//    public MgApplication getApplication() {
//        return application;
//    }
//
//    @Override
//    protected void onRun() {
//        application = new MgApplication(logicalApplication.getName());
//        todo;
//    }
//}
