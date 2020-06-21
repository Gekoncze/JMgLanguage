package cz.mg.language.entities.mg.runtime.clazzes.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgThreadR extends MgArchitectureEntityR implements Named, Runnable {
    @Value
    private final ReadableText name;

    @Link
    private MgFunctionObject currentFunctionObject = null;

    @Part
    private final List<MgClassObject> classObjects = new List<>();

    @Part
    private final List<MgFunctionObject> functionObjects = new List<>();

    public MgThreadR(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
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
