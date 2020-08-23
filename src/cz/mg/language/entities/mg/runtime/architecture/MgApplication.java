package cz.mg.language.entities.mg.runtime.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.components.MgLocation;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgApplication extends MgArchitectureObject {
    private static final MgType TYPE = new MgType("Application");

    @Part
    private final MgLocation root = new MgLocation(new ReadonlyText(""));

//    @Part
//    private final List<MgCore> cores = new List<>();
//
//    @Part
//    private final List<MgThread> threads = new List<>();

    public MgApplication(ReadableText name) {
        super(TYPE, name);
    }

    public MgLocation getRoot() {
        return root;
    }

//    public List<MgCore> getCores() {
//        return cores;
//    }
//
//    public List<MgThread> getThreads() {
//        return threads;
//    }
}
