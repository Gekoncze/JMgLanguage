package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgBoolType;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.utilities.DeclarationHelper;


public class MgCaseCommand extends MgCommand {
    public static final MgDatatype DATATYPE = new MgDatatype(
        MgBoolType.getInstance(),
        MgDatatype.Storage.ANY,
        MgDatatype.Requirement.OPTIONAL
    );

    @Optional @Part
    private final MgExpression expression;

    @Optional @Value
    private final MgInputConnector inputConnector = new MgInputConnector(DATATYPE);

    @Mandatory @Part
    private final List<MgCommand> commands = new List<>();

    public MgCaseCommand(MgExpression expression) {
        this.expression = expression;
        connect();
        this.expression.validate();
    }

    private void connect(){
        DeclarationHelper.connect(inputConnector, expression.getOutputConnectors().getFirst());
    }

    public MgExpression getExpression() {
        return expression;
    }

    public MgInputConnector getInputConnector() {
        return inputConnector;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        // expression should be handled by switch command
        for(MgCommand command : commands){
            command.run(functionInstance);
        }
    }
}
