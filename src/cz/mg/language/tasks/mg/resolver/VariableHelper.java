package cz.mg.language.tasks.mg.resolver;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public class VariableHelper {
    private static final ReadableText EXPRESSION_VARIABLE_NAME = new ReadonlyText("");

    @Mandatory @Part
    private final MgFunction function;

    public VariableHelper(MgFunction function) {
        this.function = function;
    }

    public void sink(){
        // todo - can be used for optimization
    }

    public void raise(){
        // todo - can be used for optimization
    }

    public int nextDeclaredVariable(ReadableText name, MgDatatype datatype){
        // todo - can be optimized (maybe)
        function.getLocal().addLast(new MgVariable(name, datatype));
        return function.getInput().count() + function.getOutput().count() + function.getLocal().count();
    }

    public int nextExpressionVariable(MgDatatype datatype){
        // todo - can be optimized
        function.getLocal().addLast(new MgVariable(EXPRESSION_VARIABLE_NAME, datatype));
        return function.getInput().count() + function.getOutput().count() + function.getLocal().count();
    }

    public MgVariable getLocalVariable(int index){
        int i = index - function.getInput().count() - function.getOutput().count();
        return function.getLocal().get(i);
    }
}
