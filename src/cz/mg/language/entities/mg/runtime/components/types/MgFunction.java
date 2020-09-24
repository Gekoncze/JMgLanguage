package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.ReturnException;


public class MgFunction extends MgInterface implements MgRunnable {
    private static final MgType TYPE = new MgType("Function");

    @Mandatory @Part
    private final ArrayList<MgLocalVariable> local = new ArrayList<>();

    @Optional @Part
    private MgOperator operator;

    @Mandatory @Part
    private final ArrayList<MgCommand> commands = new ArrayList<>();

    protected MgFunction(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgFunction(ReadableText name) {
        super(TYPE, name);
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

    public void updateCache(){
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

    @Override
    public void run(MgFunctionObject functionObject) {
        try {
            for(MgCommand command : commands){
                command.run(functionObject);
            }
        } catch (ReturnException e){
        }
    }
}
