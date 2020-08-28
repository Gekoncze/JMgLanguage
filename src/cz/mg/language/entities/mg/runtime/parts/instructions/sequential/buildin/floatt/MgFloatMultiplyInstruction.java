//package cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.floatt;
//
//import cz.mg.language.annotations.entity.Value;
//import cz.mg.language.entities.mg.runtime.architecture.MgThread;
//import cz.mg.language.entities.mg.runtime.atoms.MgFloatObject;
//import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
//import cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.MgBuildinInstruction;
//
//
//public class MgFloatMultiplyInstruction extends MgBuildinInstruction {
//    @Value
//    private final int sourceLeftIndex;
//
//    @Value
//    private final int sourceRightIndex;
//
//    @Value
//    private final int targetIndex;
//
//    public MgFloatMultiplyInstruction(int sourceLeftIndex, int sourceRightIndex, int targetIndex) {
//        this.sourceLeftIndex = sourceLeftIndex;
//        this.sourceRightIndex = sourceRightIndex;
//        this.targetIndex = targetIndex;
//    }
//
//    @Override
//    public MgInstruction run(MgThread thread) {
//        MgFloatObject sourceLeft = (MgFloatObject) thread.getCurrentFunctionObject().getObjects().get(sourceLeftIndex);
//        MgFloatObject sourceRight = (MgFloatObject) thread.getCurrentFunctionObject().getObjects().get(sourceRightIndex);
//        MgFloatObject target = (MgFloatObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
//        target.setValue(sourceLeft.getValue() * sourceRight.getValue());
//        return getNextInstruction();
//    }
//}
