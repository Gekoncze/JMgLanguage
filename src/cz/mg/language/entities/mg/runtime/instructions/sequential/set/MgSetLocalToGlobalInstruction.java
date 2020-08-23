//package cz.mg.language.entities.mg.runtime.instructions.sequential.set;
//
//import cz.mg.language.annotations.entity.Value;
//import cz.mg.language.entities.mg.runtime.architecture.MgThread;
//import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
//import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
//import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSequentialInstruction;
//
//
//public class MgSetLocalToGlobalInstruction extends MgSequentialInstruction {
//    @Value
//    private final int sourceIndex;
//
//    @Value
//    private final MgGlobalVariable globalVariable;
//
//    public MgSetLocalToGlobalInstruction(int sourceIndex, MgGlobalVariable globalVariable) {
//        this.sourceIndex = sourceIndex;
//        this.globalVariable = globalVariable;
//    }
//
//    @Override
//    public MgInstruction run(MgThread thread) {
//        globalVariable.setObject(thread.getCurrentFunctionObject().getObjects().get(sourceIndex));
//        return getNextInstruction();
//    }
//}
