import cz.mg.collections.array.Array;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.architecture.MgApplication;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.atoms.MgIntObject;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSequentialInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSetFieldToVariableInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.intt.MgIntPlusInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.test.MgPrintIntegerInstruction;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.entities.mg.runtime.other.MgDatatype;
import cz.mg.language.entities.mg.runtime.other.MgModifier;
import cz.mg.language.entities.mg.runtime.other.MgVariable;
import cz.mg.language.entities.mg.runtime.types.MgClass;
import cz.mg.language.entities.mg.runtime.types.MgFunction;


public class MgRuntimeTest {
    public static void main(String[] args) {
        MgApplication application = new MgApplication(new ReadonlyText("application"));

        MgClass clazz = new MgClass(new ReadonlyText("Class"));

        clazz.setClasses(new Array<>());
        clazz.setFunctions(new Array<>());
        clazz.setVariables(new Array<>(
            new MgVariable(new ReadonlyText("foo"), new MgDatatype(MgIntObject.TYPE, MgModifier.VALUE)), // 0
            new MgVariable(new ReadonlyText("bar"), new MgDatatype(MgIntObject.TYPE, MgModifier.VALUE))  // 1
        ));

        MgFunction function = new MgFunction(new ReadonlyText("function"));

        function.setInput(new Array<>());
        function.setOutput(new Array<>());

        function.setLocal(new Array<>(
            new MgVariable(new ReadonlyText("a"), new MgDatatype(MgIntObject.TYPE, MgModifier.VALUE)), // 0
            new MgVariable(new ReadonlyText("b"), new MgDatatype(MgIntObject.TYPE, MgModifier.VALUE)), // 1
            new MgVariable(new ReadonlyText("c"), new MgDatatype(MgIntObject.TYPE, MgModifier.VALUE)), // 2
            new MgVariable(new ReadonlyText("aa"), new MgDatatype(clazz, MgModifier.VALUE)),               // 3
            new MgVariable(new ReadonlyText("bb"), new MgDatatype(clazz, MgModifier.VALUE)),               // 4
            new MgVariable(new ReadonlyText("cc"), new MgDatatype(clazz, MgModifier.VALUE))                // 5
        ));

        function.setInstructions(new Array<>(
            new MgIntPlusInstruction(0, 1, 2), // c = a + b
            new MgPrintIntegerInstruction(2),             // print c
            new MgSetFieldToVariableInstruction(3, 0, 0), // a = aa.foo
            new MgSetFieldToVariableInstruction(4, 0, 1), // b = bb.foo
            new MgSetFieldToVariableInstruction(5, 0, 2), // c = cc.foo
            new MgIntPlusInstruction(0, 1, 2), // c = a + b
            new MgPrintIntegerInstruction(2)              // print c
        ));

        connect(
            function.getInstructions().get(0),
            function.getInstructions().get(1),
            function.getInstructions().get(2),
            function.getInstructions().get(3),
            function.getInstructions().get(4),
            function.getInstructions().get(5),
            function.getInstructions().get(6)
        );

        application.getRoot().getObjects().addLast(function);

        MgThread thread = new MgThread(new ReadonlyText("main"));
        application.getThreads().addLast(thread);

        MgFunctionObject functionObject = create(
            function,
            create(1),
            create(2),
            create(0),
            create(clazz, create(11), create(33)),
            create(clazz, create(22), create(44)),
            create(clazz, create(0), create(0))
        );

        thread.getFunctionObjects().addLast(functionObject);
        thread.setCurrentFunctionObject(functionObject);
        thread.run();
    }

    private static void connect(MgInstruction... instructions){
        for(int i = 0; i < instructions.length - 1; i++){
            MgSequentialInstruction left = (MgSequentialInstruction) instructions[i];
            MgInstruction right = instructions[i+1];
            left.setNextInstruction(right);
        }
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

    private static MgIntObject create(int integer){
        return new MgIntObject(integer);
    }
}
