//import cz.mg.collections.text.ReadonlyText;
//import cz.mg.language.entities.mg.runtime.clazzes.architecture.MgApplicationR;
//import cz.mg.language.entities.mg.runtime.clazzes.architecture.MgModuleR;
//import cz.mg.language.entities.mg.runtime.clazzes.architecture.MgThreadR;
//import cz.mg.language.entities.mg.runtime.components.MgFunctionR;
//import cz.mg.language.entities.mg.runtime.components.MgVariableR;
//import cz.mg.language.entities.mg.runtime.instructions.datatype.integer.MgIntegerPlusIntegerInstructionR;
//import cz.mg.language.entities.mg.runtime.instructions.test.MgPrintIntegerInstructionR;
//import cz.mg.language.entities.mg.runtime.objects.MgFunctionObjectR;
//import cz.mg.language.entities.mg.runtime.objects.elementary.MgIntegerObjectR;
//
//
//public class MgRuntimeTest {
//    public static void main(String[] args) {
//        MgApplicationR application = new MgApplicationR(new ReadonlyText("application"));
//
//        MgModuleR module = new MgModuleR(new ReadonlyText("module"));
//        application.getModules().addLast(module);
//
//        MgFunctionR functionDefinition = new MgFunctionR(new ReadonlyText("function"));
//        functionDefinition.getVariables().addLast(new MgVariableR(new ReadonlyText("a"), null));
//        functionDefinition.getVariables().addLast(new MgVariableR(new ReadonlyText("b"), null));
//        functionDefinition.getVariables().addLast(new MgVariableR(new ReadonlyText("c"), null));
//
//        functionDefinition.getInstructions().addLast(new MgIntegerPlusIntegerInstructionR(0, 1, 2));
//        functionDefinition.getInstructions().addLast(new MgPrintIntegerInstructionR(2));
//        ((MgIntegerPlusIntegerInstructionR)functionDefinition.getInstructions().getFirst()).setNextInstruction(
//                functionDefinition.getInstructions().getLast()
//        );
//
//        module.getFunctions().addLast(functionDefinition);
//
//        MgThreadR thread = new MgThreadR(new ReadonlyText("main"));
//
//        MgFunctionObjectR functionObject = new MgFunctionObjectR(functionDefinition);
//        functionObject.getObjects().set(0, new MgIntegerObjectR(7));
//        functionObject.getObjects().set(1, new MgIntegerObjectR(10));
//        functionObject.getObjects().set(2, new MgIntegerObjectR(0));
//        functionObject.setInstruction(functionDefinition.getInstructions().getFirst());
//
//        thread.getFunctionObjects().addLast(functionObject);
//        thread.setCurrentFunctionObject(functionObject);
//        thread.run();
//    }
//}
