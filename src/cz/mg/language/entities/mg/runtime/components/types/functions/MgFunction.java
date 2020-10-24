package cz.mg.language.entities.mg.runtime.components.types.functions;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.ReturnException;


public abstract class MgFunction extends MgInterface implements MgRunnable {
    @Mandatory @Part
    private final ArrayList<MgFunctionVariable> local = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgCommand> commands = new ArrayList<>();

    @Optional @Cache
    private Integer variableCountCache;

    public MgFunction(ReadableText name) {
        super(name);
    }

    public ArrayList<MgFunctionVariable> getLocal() {
        return local;
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
        for(MgFunctionVariable variable : getInput()){
            variable.setOffset(i);
            i++;
        }
        for(MgFunctionVariable variable : getOutput()){
            variable.setOffset(i);
            i++;
        }
        for(MgFunctionVariable variable : getLocal()){
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
    public void run(MgFunctionInstance functionInstance) {
        try {
            for(MgCommand command : commands){
                command.run(functionInstance);
            }
        } catch (ReturnException e){
            // nothing to do
        }
    }
}
