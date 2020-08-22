//package cz.mg.language.entities.mg.runtime.instructions.sequential.set;
//
//import cz.mg.language.annotations.entity.Value;
//import cz.mg.language.entities.mg.runtime.architecture.MgThread;
//import cz.mg.language.entities.mg.runtime.components.MgVariable;
//import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
//import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSequentialInstruction;
//import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
//import cz.mg.language.entities.mg.runtime.objects.MgObject;
//
//
//public class MgSetLocalToFieldInstruction extends MgSequentialInstruction {
//    @Value
//    private final int sourceIndex;
//
//    @Value
//    private final int targetIndex;
//
//    @Value
//    private final MgVariable targetVariable;
//
//    public MgSetLocalToFieldInstruction(int sourceIndex, int targetIndex, MgVariable targetVariable) {
//        this.sourceIndex = sourceIndex;
//        this.targetIndex = targetIndex;
//        this.targetVariable = targetVariable;
//    }
//
//    @Override
//    public MgInstruction run(MgThread thread) {
//        MgObject source = thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
//        MgClassObject target = (MgClassObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
//        int targetVariableIndex = target.getType().getVariableTable().get(targetVariable);
//        target.getObjects().set(source, targetVariableIndex);
//        return getNextInstruction();
//    }
//} todo
