package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.MgFunctionTable;
import cz.mg.language.entities.mg.runtime.parts.MgVariableTable;


public class MgClass extends MgType {
    private static final MgType TYPE = new MgType("Class");

    @Link
    private ReadableArray<MgClass> classes;

    @Part
    private ReadableArray<MgVariable> variables;

    @Part
    private ReadableArray<MgFunction> functions;

    @Part
    private ReadableArray<MgGlobalVariable> globalVariables;

    @Cache
    private MgVariableTable variableTable;

    @Cache
    private MgFunctionTable functionTable;

    protected MgClass(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgClass(ReadableText name) {
        super(TYPE, name);
    }

    public ReadableArray<MgClass> getClasses() {
        return classes;
    }

    public ReadableArray<MgVariable> getVariables() {
        return variables;
    }

    public ReadableArray<MgFunction> getFunctions() {
        return functions;
    }

    public ReadableArray<MgGlobalVariable> getGlobalVariables() {
        return globalVariables;
    }

    public MgVariableTable getVariableTable() {
        if(this.variableTable == null) createDefaultVariableTable();
        return variableTable;
    }

    public MgFunctionTable getFunctionTable() {
        if(this.functionTable == null) createDefaultFunctionTable();
        return functionTable;
    }

    public void setClasses(ReadableArray<MgClass> classes) {
        this.classes = classes;
    }

    public void setVariables(ReadableArray<MgVariable> variables) {
        this.variables = variables;
    }

    public void setFunctions(ReadableArray<MgFunction> functions) {
        this.functions = functions;
    }

    public void setGlobalVariables(ReadableArray<MgGlobalVariable> globalVariables) {
        this.globalVariables = globalVariables;
    }

    public void setFunctionTable(MgFunctionTable functionTable) {
        this.functionTable = functionTable;
    }

    public void setVariableTable(MgVariableTable variableTable) {
        this.variableTable = variableTable;
    }

    private void createDefaultVariableTable() {
        this.variableTable = new MgVariableTable();
        addClassVariables(this);
    }

    private void addClassVariables(MgClass clazz) {
        for(MgClass baseClass : clazz.getClasses()){
            addClassFunctions(baseClass);
        }

        int i = 0;
        for(MgVariable variable : clazz.getVariables()){
            this.variableTable.set(variable, i);
            i++;
        }
    }

    private void createDefaultFunctionTable() {
        this.functionTable = new MgFunctionTable();
        addClassFunctions(this);
    }

    private void addClassFunctions(MgClass clazz){
        for(MgClass baseClass : clazz.getClasses()){
            addClassFunctions(baseClass);
        }

        for(MgFunction function : clazz.getFunctions()){
            this.functionTable.set(function, function);
        }
    }

    @Override
    public boolean is(MgType type) {
        if(type == this) return true;
        for(MgClass base : classes){
            if(base.is(type)) return true;
        }
        return false;
    }
}
