package cz.mg.compiler.entities.logic.mg.instructions.datatype.integer;

import cz.mg.compiler.annotations.entity.Link;
import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.mg.architecture.MgThread;
import cz.mg.compiler.entities.logic.mg.instructions.MgInstruction;
import cz.mg.compiler.entities.logic.mg.instructions.datatype.MgDatatypeInstruction;
import cz.mg.compiler.entities.logic.mg.objects.elementary.MgIntegerObject;


public class MgIntegerDivideIntegerInstruction extends MgDatatypeInstruction {
    @Value
    private final int sourceLeftIndex;

    @Value
    private final int sourceRightIndex;

    @Value
    private final int targetIndex;

    @Link
    private final MgInstruction nextInstruction;

    public MgIntegerDivideIntegerInstruction(int sourceLeftIndex, int sourceRightIndex, int targetIndex, MgInstruction nextInstruction) {
        this.sourceLeftIndex = sourceLeftIndex;
        this.sourceRightIndex = sourceRightIndex;
        this.targetIndex = targetIndex;
        this.nextInstruction = nextInstruction;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgIntegerObject sourceLeft = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceLeftIndex);
        MgIntegerObject sourceRight = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceRightIndex);
        MgIntegerObject target = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.setValue(sourceLeft.getValue() / sourceRight.getValue());
        return nextInstruction;
    }
}
