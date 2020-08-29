package cz.mg.language.tasks.mg.resolver;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public class VariableHelper {
    private static final ReadableText EXPRESSION_VARIABLE_NAME = new ReadonlyText("");

    @Mandatory @Part
    private final List<MgVariable> declaredVariables = new List<>();

    @Mandatory @Part
    private final List<MgVariable> expressionVariables = new List<>();

    public VariableHelper() {
    }

    public List<MgVariable> getDeclaredVariables() {
        return declaredVariables;
    }

    public List<MgVariable> getExpressionVariables() {
        return expressionVariables;
    }

    public void sink(){
        // todo - can be used for optimization
    }

    public void raise(){
        // todo - can be used for optimization
    }

    public MgVariable nextDeclaredVariable(ReadableText name, MgDatatype datatype){
        MgVariable variable = new MgVariable(name, datatype);
        declaredVariables.addLast(variable);
        return variable;
    }

    public MgVariable nextExpressionVariable(MgDatatype datatype){
        // todo - can be optimized
        MgVariable variable = new MgVariable(EXPRESSION_VARIABLE_NAME, datatype);
        expressionVariables.addLast(variable);
        return variable;
    }
}
