//package cz.mg.language.entities.mg.runtime.instructions.sequential;
//
//import cz.mg.collections.array.Array;
//import cz.mg.language.annotations.entity.Link;
//import cz.mg.language.entities.mg.runtime.architecture.MgThread;
//import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
//import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
//import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
//
//
//public class MgDestroyFunctionInstruction extends MgSequentialInstruction {
//    @Link
//    private final MgFunction function;
//
//    @Link
//    private final Array<Integer> outputOffset;
//
//    public MgDestroyFunctionInstruction(MgFunction function, Array<Integer> outputOffset) {
//        this.function = function;
//        this.outputOffset = outputOffset;
//    }
//
//    @Override
//    public MgInstruction run(MgThread thread) {
//        MgFunctionObject nextFunctionObject = thread.getFunctionObjects().removeLast();
//        MgFunctionObject prevFunctionObject = thread.getFunctionObjects().getLast();
//        for(int i = 0; i < outputOffset.count(); i++){
//            int delta = function.getInput().count();
//            prevFunctionObject.getObjects().set(nextFunctionObject.getObjects().get(delta + i), outputOffset.get(i));
//        }
//        thread.setCurrentFunctionObject(prevFunctionObject);
//        return getNextInstruction();
//    }
//}
