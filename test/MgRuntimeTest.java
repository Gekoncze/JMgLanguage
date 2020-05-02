import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.logic.mg.architecture.MgApplication;
import cz.mg.language.entities.logic.mg.architecture.MgModule;
import cz.mg.language.entities.logic.mg.architecture.MgThread;
import cz.mg.language.entities.logic.mg.definitions.MgFunction;
import cz.mg.language.entities.logic.mg.definitions.MgVariable;
import cz.mg.language.entities.logic.mg.instructions.datatype.integer.MgIntegerPlusIntegerInstruction;
import cz.mg.language.entities.logic.mg.instructions.test.MgPrintIntegerInstruction;
import cz.mg.language.entities.logic.mg.objects.MgFunctionObject;
import cz.mg.language.entities.logic.mg.objects.elementary.MgIntegerObject;


public class MgRuntimeTest {
    public static void main(String[] args) {
        MgApplication application = new MgApplication(new ReadonlyText("application"));

        MgModule module = new MgModule(new ReadonlyText("module"));
        application.getModules().addLast(module);

        MgFunction functionDefinition = new MgFunction(new ReadonlyText("function"));
        functionDefinition.getVariables().addLast(new MgVariable(new ReadonlyText("a"), null));
        functionDefinition.getVariables().addLast(new MgVariable(new ReadonlyText("b"), null));
        functionDefinition.getVariables().addLast(new MgVariable(new ReadonlyText("c"), null));

        functionDefinition.getInstructions().addLast(new MgIntegerPlusIntegerInstruction(0, 1, 2));
        functionDefinition.getInstructions().addLast(new MgPrintIntegerInstruction(2));
        ((MgIntegerPlusIntegerInstruction)functionDefinition.getInstructions().getFirst()).setNextInstruction(
                functionDefinition.getInstructions().getLast()
        );

        module.getFunctions().addLast(functionDefinition);

        MgThread thread = new MgThread(new ReadonlyText("main"));

        MgFunctionObject functionObject = new MgFunctionObject(functionDefinition);
        functionObject.getObjects().set(0, new MgIntegerObject(7));
        functionObject.getObjects().set(1, new MgIntegerObject(10));
        functionObject.getObjects().set(2, new MgIntegerObject(0));
        functionObject.setInstruction(functionDefinition.getInstructions().getFirst());

        thread.getFunctionObjects().addLast(functionObject);
        thread.setCurrentFunctionObject(functionObject);
        thread.run();
    }
}
