import cz.mg.collections.array.Array;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.architecture.MgApplication;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.atoms.MgIntegerObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.other.MgVariable;
import cz.mg.language.entities.mg.runtime.instructions.buildin.integer.MgIntegerPlusIntegerInstruction;
import cz.mg.language.entities.mg.runtime.instructions.test.MgPrintIntegerInstruction;
import cz.mg.language.entities.mg.runtime.types.MgFunction;


public class MgRuntimeTest {
    public static void main(String[] args) {
        MgApplication application = new MgApplication(new ReadonlyText("application"));

        MgFunction function = new MgFunction(new ReadonlyText("function"));

        function.setLocal(new Array<>(
            new MgVariable(new ReadonlyText("a"), null),
            new MgVariable(new ReadonlyText("b"), null),
            new MgVariable(new ReadonlyText("c"), null)
        ));

        function.setInstructions(new Array<>(
            new MgIntegerPlusIntegerInstruction(0, 1, 2),
            new MgPrintIntegerInstruction(2)
        ));

        // todo - is there any better way to connect instructions?
        ((MgIntegerPlusIntegerInstruction)function.getInstructions().getFirst()).setNextInstruction(
            function.getInstructions().getLast()
        );

        application.getRoot().getObjects().addLast(function);

        MgThread thread = new MgThread(new ReadonlyText("main"));
        application.getThreads().addLast(thread);

        MgFunctionObject functionObject = new MgFunctionObject(function);
        functionObject.getObjects().set(new MgIntegerObject(7), 0);
        functionObject.getObjects().set(new MgIntegerObject(10), 1);
        functionObject.getObjects().set(new MgIntegerObject(0), 2);
        functionObject.setInstruction(function.getInstructions().getFirst());

        thread.getFunctionObjects().addLast(functionObject);
        thread.setCurrentFunctionObject(functionObject);
        thread.run();
    }
}
