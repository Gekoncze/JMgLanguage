package cz.mg.language.tasks.mg.resolver;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.runtime.atoms.MgBoolObject;
import cz.mg.language.entities.mg.runtime.atoms.MgFloatObject;
import cz.mg.language.entities.mg.runtime.atoms.MgIntObject;
import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.entities.mg.runtime.components.MgStamp;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public class MgAddBuildinComponentsTask extends MgResolverTask {
    @Input
    private final MgLocation root;

    public MgAddBuildinComponentsTask(MgLocation root) {
        this.root = root;
    }

    @Override
    protected void onRun() {
        MgLocation cz = new MgLocation(new ReadonlyText("cz"));
        MgLocation mg = new MgLocation(new ReadonlyText("mg"));
        MgLocation types = new MgLocation(new ReadonlyText("types"));
        MgLocation stamps = new MgLocation(new ReadonlyText("stamps"));
        cz.getObjects().addLast(mg);
        mg.getObjects().addLast(types);
        mg.getObjects().addLast(stamps);
        root.getObjects().addLast(cz);

        MgDatatype nullDatatype = new MgDatatype(MgObject.TYPE, MgDatatype.Storage.INDIRECT, MgDatatype.Requirement.OPTIONAL);
        mg.getObjects().addLast(new MgGlobalVariable(new ReadonlyText("null"), nullDatatype, null));

        types.getObjects().addLast(MgBoolObject.TYPE);
        types.getObjects().addLast(MgIntObject.TYPE);
        types.getObjects().addLast(MgFloatObject.TYPE);

        stamps.getObjects().addLast(new MgStamp(new ReadonlyText("public")));
        stamps.getObjects().addLast(new MgStamp(new ReadonlyText("private")));

        stamps.getObjects().addLast(new MgStamp(new ReadonlyText("value")));
        stamps.getObjects().addLast(new MgStamp(new ReadonlyText("part")));
        stamps.getObjects().addLast(new MgStamp(new ReadonlyText("shared")));
        stamps.getObjects().addLast(new MgStamp(new ReadonlyText("link")));
    }
}
