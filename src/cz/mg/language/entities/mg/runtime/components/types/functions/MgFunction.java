package cz.mg.language.entities.mg.runtime.components.types.functions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.Clump;
import cz.mg.collections.list.ArrayList;
import cz.mg.collections.special.CompositeCollection;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.ReturnException;


public abstract class MgFunction extends MgInterface implements MgRunnable {
    @Mandatory @Part
    private final ArrayList<MgInstanceVariable> localVariables = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgCommand> commands = new ArrayList<>();

    @Optional @Cache
    private Integer variableCountCache;

    public MgFunction(ReadableText name) {
        super(name);
    }

    public ArrayList<MgInstanceVariable> getLocalVariables() {
        return localVariables;
    }

    public ArrayList<MgCommand> getCommands() {
        return commands;
    }

    public Integer getVariableCountCache() {
        if(variableCountCache == null) updateVariableCountCache();
        return variableCountCache;
    }

    private void updateVariableCountCache(){
        variableCountCache =
            getInputVariables().count() +
            getOutputVariables().count() +
            getLocalVariables().count();
    }

    @Override
    public Clump<? extends MgVariable> getVariables() {
        return new CompositeCollection<>(getInputVariables(), getOutputVariables(), getLocalVariables());
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
