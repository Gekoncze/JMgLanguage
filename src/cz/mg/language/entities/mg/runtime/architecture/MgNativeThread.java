package cz.mg.language.entities.mg.runtime.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgNativeThread extends MgArchitectureObject {
    private static final MgType TYPE = new MgType("NativeThread");

    @Part
    private Thread thread;

    @Part
    private MgFunctionObject functionObject;

    public MgNativeThread(ReadableText name) {
        super(TYPE, name);
    }

    public MgNativeThread(MgType type, ReadableText name, MgFunctionObject functionObject) {
        super(type, name);
        this.functionObject = functionObject;
    }

    public void run(MgFunction function){
        if(thread != null) throw new RuntimeException();
        functionObject = new MgFunctionObject(function);
        thread = new Thread(() -> {
            function.run(functionObject);
            functionObject = null;
            thread = null;
        });
        thread.start();
    }
}
