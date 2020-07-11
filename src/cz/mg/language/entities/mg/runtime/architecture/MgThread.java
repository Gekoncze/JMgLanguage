package cz.mg.language.entities.mg.runtime.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgThread extends MgArchitectureObject implements Runnable {
    private static final MgType TYPE = new MgType("Thread");

    @Link
    private MgFunctionObject currentFunctionObject;

    @Part
    private final List<MgClassObject> classObjects = new List<>();

    @Part
    private final List<MgFunctionObject> functionObjects = new List<>();

    public MgThread(ReadableText name){
        super(TYPE, name);
    }

    public MgFunctionObject getCurrentFunctionObject() {
        return currentFunctionObject;
    }

    public void setCurrentFunctionObject(MgFunctionObject currentFunctionObject) {
        this.currentFunctionObject = currentFunctionObject;
    }

    public List<MgClassObject> getClassObjects() {
        return classObjects;
    }

    public List<MgFunctionObject> getFunctionObjects() {
        return functionObjects;
    }

    @Override
    public void run(){
        while(currentFunctionObject != null){
            if(currentFunctionObject.getInstruction() != null){
                currentFunctionObject.setInstruction(currentFunctionObject.getInstruction().run(this));
            } else {
                break;
            }
        }
    }
}
