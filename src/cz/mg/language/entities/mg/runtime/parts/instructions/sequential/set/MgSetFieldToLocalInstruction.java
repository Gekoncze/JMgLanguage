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
//public class MgSetFieldToLocalInstruction extends MgSequentialInstruction {
//    @Value
//    private final int sourceIndex;
//
//    @Value
//    private final MgVariable sourceVariable;
//
//    @Value
//    private final int targetIndex;
//
//    public MgSetFieldToLocalInstruction(int sourceIndex, MgVariable sourceVariable, int targetIndex) {
//        this.sourceIndex = sourceIndex;
//        this.sourceVariable = sourceVariable;
//        this.targetIndex = targetIndex;
//    }
//
//    @Override
//    public MgInstruction run(MgThread thread) {
//        MgClassObject source = (MgClassObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
//        int sourceVariableIndex = source.getType().getVariableTable().get(sourceVariable);
//        MgObject sourceMember = source.getObjects().get(sourceVariableIndex);
//        thread.getCurrentFunctionObject().getObjects().set(sourceMember, targetIndex);
//        return getNextInstruction();
//    }
//}
