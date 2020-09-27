package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.roles.MgRunnable;
import cz.mg.language.entities.mg.runtime.roles.MgType;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.ReturnException;


public class MgFunction extends MgInterface implements MgType, MgRunnable {
    @Mandatory @Part
    private final ArrayList<MgLocalVariable> local = new ArrayList<>();

    @Optional @Part
    private MgOperator operator;

    @Mandatory @Part
    private final ArrayList<MgCommand> commands = new ArrayList<>();

    @Optional @Cache
    private Integer variableCountCache;

    public MgFunction(ReadableText name) {
        super(name);
    }

    public MgFunction(ReadableText name, MgOperator operator) {
        super(name);
        this.operator = operator;
    }

    public ArrayList<MgLocalVariable> getLocal() {
        return local;
    }

    public MgOperator getOperator() {
        return operator;
    }

    public void setOperator(MgOperator operator) {
        this.operator = operator;
    }

    public ArrayList<MgCommand> getCommands() {
        return commands;
    }

    public Integer getVariableCountCache() {
        if(variableCountCache == null) updateVariableCountCache();
        return variableCountCache;
    }

    public void updateVariableOffsetCache(){
        int i = 0;
        for(MgLocalVariable variable : getInput()){
            variable.setOffset(i);
            i++;
        }
        for(MgLocalVariable variable : getOutput()){
            variable.setOffset(i);
            i++;
        }
        for(MgLocalVariable variable : getLocal()){
            variable.setOffset(i);
            i++;
        }
    }

    private void updateVariableCountCache(){
        variableCountCache =
            getInput().count() +
            getOutput().count() +
            getLocal().count();
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject) {
        try {
            for(MgCommand command : commands){
                command.run(functionObject);
            }
        } catch (ReturnException e){
            // nothing to do
        }
    }

    @Override
    public boolean is(MgType baseType) {
        return this == baseType;
    }
}
