package cz.mg.language.tasks.mg.resolvers;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.architecture.MgLogicalBuildinLocation;
import cz.mg.language.entities.mg.logical.architecture.MgLogicalLocation;
import cz.mg.language.entities.mg.logical.components.buidin.MgLogicalBuildinClass;
import cz.mg.language.entities.mg.logical.components.buidin.MgLogicalBuildinStamp;


public class MgAddBuildinComponentsTask extends MgResolverTask {
    @Input
    private final MgLogicalLocation root;

    public MgAddBuildinComponentsTask(MgLogicalLocation root) {
        this.root = root;
    }

    @Override
    protected void onRun() {
        MgLogicalBuildinLocation cz = new MgLogicalBuildinLocation("cz");
        MgLogicalBuildinLocation mg = new MgLogicalBuildinLocation("mg");
        MgLogicalBuildinLocation types = new MgLogicalBuildinLocation("types");
        MgLogicalBuildinLocation stamps = new MgLogicalBuildinLocation("stamps");
        cz.getLocations().addLast(mg);
        mg.getLocations().addLast(types);
        mg.getLocations().addLast(stamps);
        root.getLocations().addLast(cz);

        types.getComponents().addLast(new MgLogicalBuildinClass("Bool"));
        types.getComponents().addLast(new MgLogicalBuildinClass("Int"));
        types.getComponents().addLast(new MgLogicalBuildinClass("Float"));

        stamps.getComponents().addLast(new MgLogicalBuildinStamp("public"));
        stamps.getComponents().addLast(new MgLogicalBuildinStamp("private"));
    }
}
