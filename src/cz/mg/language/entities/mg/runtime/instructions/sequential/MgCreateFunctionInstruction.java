package cz.mg.language.entities.mg.runtime.instructions.sequential;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;


public class MgCreateFunctionInstruction extends MgSequentialInstruction {
    @Link
    private final MgFunction function;

    @Value
    private final Array<Integer> inputOffset;

    public MgCreateFunctionInstruction(MgFunction function, Array<Integer> inputOffset) {
        this.function = function;
        this.inputOffset = inputOffset;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgFunctionObject prevFunctionObject = thread.getFunctionObjects().getLast();
        MgFunctionObject nextFunctionObject = new MgFunctionObject(function);
        thread.getFunctionObjects().addLast(nextFunctionObject);
        for(int i = 0; i < inputOffset.count(); i++){
            nextFunctionObject.getObjects().set(prevFunctionObject.getObjects().get(inputOffset.get(i)), i);
        }
        thread.setCurrentFunctionObject(nextFunctionObject);
        return getNextInstruction();
    }
}
