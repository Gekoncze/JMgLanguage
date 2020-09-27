package cz.mg.language.tasks.mg.resolver.main;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.mg.runtime.buildin.types.*;
import cz.mg.language.entities.mg.runtime.roles.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.entities.mg.runtime.components.MgStamp;
import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.roles.MgObject;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;


public class MgAddBuildinComponentsTask extends MgResolverTask {
    @Input
    private final MgLocation root;

    public MgAddBuildinComponentsTask(MgLocation root) {
        this.root = root;
    }

    @Override
    protected void onRun() {
        MgLocation mg = open(root, "cz", "mg");
        MgLocation types = open(root, "cz", "mg", "types");
        MgLocation stamps = open(root, "cz", "mg", "stamps");

        MgDatatype voidDatatype = new MgDatatype(MgObjectType.getInstance(), MgDatatype.Storage.INDIRECT, MgDatatype.Requirement.OPTIONAL);
        MgGlobalVariable voidVariable = new MgGlobalVariable(new ReadonlyText("void"), voidDatatype);
        mg.getComponents().addLast(voidVariable);

        types.getComponents().addLast(MgBoolType.getInstance());
        types.getComponents().addLast(MgIntType.getInstance());
        types.getComponents().addLast(MgFloatType.getInstance());
        types.getComponents().addLast(MgTextType.getInstance());

        stamps.getComponents().addLast(new MgStamp(new ReadonlyText("public")));
        stamps.getComponents().addLast(new MgStamp(new ReadonlyText("private")));

        stamps.getComponents().addLast(new MgStamp(new ReadonlyText("value")));
        stamps.getComponents().addLast(new MgStamp(new ReadonlyText("part")));
        stamps.getComponents().addLast(new MgStamp(new ReadonlyText("shared")));
        stamps.getComponents().addLast(new MgStamp(new ReadonlyText("link")));
    }

    private static MgLocation open(MgLocation root, String... path){
        MgLocation cwd = root;
        for(String name : path){
            cwd = open(cwd, name);
        }
        return cwd;
    }

    private static MgLocation open(MgLocation parent, String name){
        for(MgComponent component : parent.getComponents()){
            if(component instanceof MgLocation){
                MgLocation location = (MgLocation) component;
                if(location.getName().toString().equals(name)){
                    return location;
                }
            }
        }
        MgLocation newLocation = new MgLocation(new ReadonlyText(name));
        parent.getComponents().addLast(newLocation);
        return newLocation;
    }
}
