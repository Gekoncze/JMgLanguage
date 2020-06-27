import cz.mg.collections.array.Array;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.architecture.MgApplication;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.atoms.MgIntegerObject;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSequentialInstruction;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.entities.mg.runtime.other.MgVariable;
import cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.integer.MgIntegerPlusIntegerInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.test.MgPrintIntegerInstruction;
import cz.mg.language.entities.mg.runtime.types.MgClass;
import cz.mg.language.entities.mg.runtime.types.MgFunction;


public class MgRuntimeTest {
    public static void main(String[] args) {
        MgApplication application = new MgApplication(new ReadonlyText("application"));

        MgFunction function = new MgFunction(new ReadonlyText("function"));

        function.setInput(new Array<>());
        function.setOutput(new Array<>());

        function.setLocal(new Array<>(
            new MgVariable(new ReadonlyText("a"), null),
            new MgVariable(new ReadonlyText("b"), null),
            new MgVariable(new ReadonlyText("c"), null)
        ));

        function.setInstructions(new Array<>(
            new MgIntegerPlusIntegerInstruction(0, 1, 2),
            new MgPrintIntegerInstruction(2)
        ));

        connect(
            function.getInstructions().getFirst(),
            function.getInstructions().getLast()
        );

        application.getRoot().getObjects().addLast(function);

        MgThread thread = new MgThread(new ReadonlyText("main"));
        application.getThreads().addLast(thread);

        MgFunctionObject functionObject = create(function, create(7), create(10), create(0));
        thread.getFunctionObjects().addLast(functionObject);
        thread.setCurrentFunctionObject(functionObject);
        thread.run();
    }

    private static void connect(MgInstruction left, MgInstruction right){
        ((MgSequentialInstruction) left).setNextInstruction(right);
    }

    private static MgFunctionObject create(MgFunction function, MgObject... objects){
        MgFunctionObject functionObject = new MgFunctionObject(function);
        for(int i = 0; i < objects.length; i++){
            functionObject.getObjects().set(objects[i], i);
        }
        functionObject.setInstruction(function.getInstructions().getFirst());
        return functionObject;
    }

    private static MgClassObject create(MgClass clazz, MgObject... objects){
        MgClassObject classObject = new MgClassObject(clazz);
        for(int i = 0; i < objects.length; i++){
            classObject.getObjects().set(objects[i], i);
        }
        return classObject;
    }

    private static MgIntegerObject create(int integer){
        return new MgIntegerObject(integer);
    }
}
