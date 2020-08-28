//package cz.mg.language.entities.mg.runtime.instructions.sequential.test;
//
//import cz.mg.language.annotations.entity.Value;
//import cz.mg.language.entities.mg.runtime.architecture.MgThread;
//import cz.mg.language.entities.mg.runtime.atoms.MgIntObject;
//import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
//
//
//public class MgPrintIntegerInstruction extends MgTestInstruction {
//    @Value
//    private final int sourceIndex;
//
//    public MgPrintIntegerInstruction(int sourceIndex) {
//        this.sourceIndex = sourceIndex;
//    }
//
//    @Override
//    public MgInstruction run(MgThread thread) {
//        MgIntObject integer = (MgIntObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
//        System.out.println(integer.getValue());
//        return getNextInstruction();
//    }
//}
