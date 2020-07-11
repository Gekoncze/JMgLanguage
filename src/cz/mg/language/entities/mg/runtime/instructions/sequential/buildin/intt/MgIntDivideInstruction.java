package cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.intt;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.atoms.MgIntObject;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.MgBuildinInstruction;


public class MgIntDivideInstruction extends MgBuildinInstruction {
    @Value
    private final int sourceLeftIndex;

    @Value
    private final int sourceRightIndex;

    @Value
    private final int targetIndex;

    public MgIntDivideInstruction(int sourceLeftIndex, int sourceRightIndex, int targetIndex) {
        this.sourceLeftIndex = sourceLeftIndex;
        this.sourceRightIndex = sourceRightIndex;
        this.targetIndex = targetIndex;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgIntObject sourceLeft = (MgIntObject) thread.getCurrentFunctionObject().getObjects().get(sourceLeftIndex);
        MgIntObject sourceRight = (MgIntObject) thread.getCurrentFunctionObject().getObjects().get(sourceRightIndex);
        MgIntObject target = (MgIntObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.setValue(sourceLeft.getValue() / sourceRight.getValue());
        return getNextInstruction();
    }
}
