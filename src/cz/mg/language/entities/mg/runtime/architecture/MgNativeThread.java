package cz.mg.language.entities.mg.runtime.architecture;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;


public class MgNativeThread extends MgArchitecture {
    @Part
    private Thread thread;

    @Part
    private MgFunctionInstanceImpl functionObject;

    public MgNativeThread(ReadableText name) {
        super(name);
    }

    public void run(MgFunction function){
        if(thread != null) throw new RuntimeException();
        functionObject = new MgFunctionInstanceImpl(function);
        thread = new Thread(() -> {
            function.run(functionObject);
            functionObject = null;
            thread = null;
        });
        thread.start();
    }
}
