package cz.mg.language.entities.mg.runtime.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.objects.MgClassObjectR;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObjectR;


public class MgThreadR extends MgArchitectureEntityR implements Named, Runnable {
    @Value
    private final ReadableText name;

    @Link
    private MgFunctionObjectR currentFunctionObject = null;

    @Part
    private final List<MgClassObjectR> classObjects = new List<>();

    @Part
    private final List<MgFunctionObjectR> functionObjects = new List<>();

    public MgThreadR(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public MgFunctionObjectR getCurrentFunctionObject() {
        return currentFunctionObject;
    }

    public void setCurrentFunctionObject(MgFunctionObjectR currentFunctionObject) {
        this.currentFunctionObject = currentFunctionObject;
    }

    public List<MgClassObjectR> getClassObjects() {
        return classObjects;
    }

    public List<MgFunctionObjectR> getFunctionObjects() {
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
