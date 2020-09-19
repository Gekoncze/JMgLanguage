//package cz.mg.language.entities.mg.runtime.instructions.sequential.set;
//
//import cz.mg.language.annotations.entity.Value;
//import cz.mg.language.entities.mg.runtime.architecture.MgThread;
//import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
//import cz.mg.language.entities.mg.runtime.components.MgVariable;
//import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
//import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSequentialInstruction;
//import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
//import cz.mg.language.entities.mg.runtime.objects.MgObject;
//
//
//public class MgSetFieldToGlobalInstruction extends MgSequentialInstruction {
//    @Value
//    private final int sourceIndex;
//
//    @Value
//    private final MgVariable sourceVariable;
//
//    @Value
//    private final MgGlobalVariable globalVariable;
//
//    public MgSetFieldToGlobalInstruction(int sourceIndex, MgVariable sourceVariable, MgGlobalVariable globalVariable) {
//        this.sourceIndex = sourceIndex;
//        this.sourceVariable = sourceVariable;
//        this.globalVariable = globalVariable;
//    }
//
//    @Override
//    public MgInstruction run(MgThread thread) {
//        MgClassObject source = (MgClassObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
//        int sourceVariableIndex = source.getType().getVariableTable().get(sourceVariable);
//        MgObject sourceMember = source.getObjects().get(sourceVariableIndex);
//        globalVariable.setObject(sourceMember);
//        return getNextInstruction();
//    }
//}
