package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;


public class MgFunction extends MgInterface implements MgRunnable {
    private static final MgType TYPE = new MgType("Function");

    @Mandatory @Part
    private final ArrayList<MgVariable> local = new ArrayList<>();

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

    public ArrayList<MgVariable> getLocal() {
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

    @Override
    public void run(MgFunctionObject functionObject) {
        for(MgCommand command : commands){
            command.run(functionObject);
        }
    }
}
