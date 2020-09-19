//package cz.mg.language.entities.mg.runtime.instructions.sequential.set;
//
//import cz.mg.language.annotations.entity.Value;
//import cz.mg.language.entities.mg.runtime.architecture.MgThread;
//import cz.mg.language.entities.mg.runtime.components.MgVariable;
//import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
//import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSequentialInstruction;
//import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
//
//
//public class MgSetFieldToFieldInstruction extends MgSequentialInstruction {
//    @Value
//    private final int sourceIndex;
//
//    @Value
//    private final MgVariable sourceVariable;
//
//    @Value
//    private final int targetIndex;
//
//    @Value
//    private final MgVariable targetVariable;
//
//    public MgSetFieldToFieldInstruction(int sourceIndex, MgVariable sourceVariable, int targetIndex, MgVariable targetVariable) {
//        this.sourceIndex = sourceIndex;
//        this.sourceVariable = sourceVariable;
//        this.targetIndex = targetIndex;
//        this.targetVariable = targetVariable;
//    }
//
//    @Override
//    public MgInstruction run(MgThread thread) {
//        MgClassObject source = (MgClassObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
//        MgClassObject target = (MgClassObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
//        int sourceVariableIndex = source.getType().getVariableTable().get(sourceVariable);
//        int targetVariableIndex = target.getType().getVariableTable().get(targetVariable);
//        target.getObjects().set(source.getObjects().get(sourceVariableIndex), targetVariableIndex);
//        return getNextInstruction();
//    }
//}
