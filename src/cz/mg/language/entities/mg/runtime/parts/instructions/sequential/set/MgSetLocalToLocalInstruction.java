//package cz.mg.language.entities.mg.runtime.instructions.sequential.set;
//
//import cz.mg.language.annotations.entity.Value;
//import cz.mg.language.entities.mg.runtime.architecture.MgThread;
//import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
//import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSequentialInstruction;
//import cz.mg.language.entities.mg.runtime.objects.MgObject;
//
//
//public class MgSetLocalToLocalInstruction extends MgSequentialInstruction {
//    @Value
//    private final int sourceIndex;
//
//    @Value
//    private final int targetIndex;
//
//    public MgSetLocalToLocalInstruction(int sourceIndex, int targetIndex) {
//        this.sourceIndex = sourceIndex;
//        this.targetIndex = targetIndex;
//    }
//
//    @Override
//    public MgInstruction run(MgThread thread) {
//        MgObject source = thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
//        thread.getCurrentFunctionObject().getObjects().set(source, targetIndex);
//        return getNextInstruction();
//    }
//}
