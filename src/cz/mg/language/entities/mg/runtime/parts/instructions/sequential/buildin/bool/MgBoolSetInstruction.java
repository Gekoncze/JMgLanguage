//package cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.bool;
//
//import cz.mg.language.annotations.entity.Value;
//import cz.mg.language.entities.mg.runtime.architecture.MgThread;
//import cz.mg.language.entities.mg.runtime.atoms.MgBoolObject;
//import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
//import cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.MgBuildinInstruction;
//
//
//public class MgBoolSetInstruction extends MgBuildinInstruction {
//    @Value
//    private final int sourceIndex;
//
//    @Value
//    private final int targetIndex;
//
//    public MgBoolSetInstruction(int sourceIndex, int targetIndex) {
//        this.sourceIndex = sourceIndex;
//        this.targetIndex = targetIndex;
//    }
//
//    @Override
//    public MgInstruction run(MgThread thread) {
//        MgBoolObject source = (MgBoolObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
//        MgBoolObject target = (MgBoolObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
//        target.setValue(source.getValue());
//        return getNextInstruction();
//    }
//}
