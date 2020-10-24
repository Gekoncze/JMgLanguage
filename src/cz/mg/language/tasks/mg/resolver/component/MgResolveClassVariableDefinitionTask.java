package cz.mg.language.tasks.mg.resolver.component;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;
import cz.mg.language.entities.mg.runtime.components.variables.MgClassVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgTypeVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.component.VariableContext;


public class MgResolveClassVariableDefinitionTask extends MgResolveVariableDefinitionTask {
    @Input
    private final MgLogicalVariable logicalVariable;

    @Output
    private MgVariable variable;

    public MgResolveClassVariableDefinitionTask(Context context, MgLogicalVariable logicalVariable) {
        super(new VariableContext(context), logicalVariable);
        this.logicalVariable = logicalVariable;
    }

    public MgVariable getVariable() {
        return variable;
    }

    @Override
    protected void onResolveComponent(List<MgStamp> stamps) {
        switch (getType(stamps, Type.GLOBAL)) {
            case GLOBAL:
                variable = new MgGlobalVariable(logicalVariable.getName());
                break;
            case TYPE:
                variable = new MgTypeVariable(logicalVariable.getName());
                break;
            case INSTANCE:
                variable = new MgClassVariable(logicalVariable.getName());
                break;
            default:
                throw new RuntimeException();
        }
        variable.getStamps().addCollectionLast(stamps);
        getContext().setVariable(variable);
        resolveDatatype(logicalVariable);
    }
}
