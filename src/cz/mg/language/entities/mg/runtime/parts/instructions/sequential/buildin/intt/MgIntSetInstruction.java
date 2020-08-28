//package cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.intt;
//
//import cz.mg.language.annotations.entity.Value;
//import cz.mg.language.entities.mg.runtime.architecture.MgThread;
//import cz.mg.language.entities.mg.runtime.atoms.MgIntObject;
//import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
//import cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.MgBuildinInstruction;
//
//
//public class MgIntSetInstruction extends MgBuildinInstruction {
//    @Value
//    private final int sourceIndex;
//
//    @Value
//    private final int targetIndex;
//
//    public MgIntSetInstruction(int sourceIndex, int targetIndex) {
//        this.sourceIndex = sourceIndex;
//        this.targetIndex = targetIndex;
//    }
//
//    @Override
//    public MgInstruction run(MgThread thread) {
//        MgIntObject source = (MgIntObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
//        MgIntObject target = (MgIntObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
//        target.setValue(source.getValue());
//        return getNextInstruction();
//    }
//}
