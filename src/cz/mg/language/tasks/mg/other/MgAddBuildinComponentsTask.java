package cz.mg.language.tasks.mg.other;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.logical.components.buidin.MgLogicalBuildinLocation;
import cz.mg.language.entities.mg.logical.components.MgLogicalLocation;
import cz.mg.language.entities.mg.logical.components.buidin.MgLogicalBuildinClass;
import cz.mg.language.entities.mg.logical.components.buidin.MgLogicalBuildinStamp;
import cz.mg.language.tasks.Task;


public class MgAddBuildinComponentsTask extends Task {
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
        cz.getComponents().addLast(mg);
        mg.getComponents().addLast(types);
        mg.getComponents().addLast(stamps);
        root.getComponents().addLast(cz);

        types.getComponents().addLast(new MgLogicalBuildinClass("Bool"));
        types.getComponents().addLast(new MgLogicalBuildinClass("Int"));
        types.getComponents().addLast(new MgLogicalBuildinClass("Float"));

        stamps.getComponents().addLast(new MgLogicalBuildinStamp("public"));
        stamps.getComponents().addLast(new MgLogicalBuildinStamp("private"));

        stamps.getComponents().addLast(new MgLogicalBuildinStamp("value"));
        stamps.getComponents().addLast(new MgLogicalBuildinStamp("part"));
        stamps.getComponents().addLast(new MgLogicalBuildinStamp("shared"));
        stamps.getComponents().addLast(new MgLogicalBuildinStamp("link"));
    }
}
