package cz.mg.language.tasks.mg.builder.part;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.logical.parts.MgLogicalDatatype;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Operator;
import cz.mg.language.entities.text.structured.parts.leaves.names.ObjectName;
import cz.mg.language.entities.text.structured.parts.leaves.names.TypeName;


public class MgBuildDeclarationTask extends MgBuildPartTask {
    @Output
    private MgLogicalVariable variable;

    public MgBuildDeclarationTask(List<Part> parts) {
        super(parts);
    }

    public MgLogicalVariable getVariable() {
        return variable;
    }

    @Override
    protected void buildParts(List<Part> parts) {
        checkCount(3);
        TypeName typeName = get(TypeName.class, 0);
        Operator operator = get(Operator.class, 1);
        ObjectName objectName = get(ObjectName.class, 2);

        MgLogicalDatatype.Storage storage;
        MgLogicalDatatype.Requirement requirement;
        switch (operator.getText().toString()){
            case "&":
                storage = MgLogicalDatatype.Storage.INDIRECT;
                requirement = MgLogicalDatatype.Requirement.MANDATORY;
                break;
            case "&?":
                storage = MgLogicalDatatype.Storage.INDIRECT;
                requirement = MgLogicalDatatype.Requirement.OPTIONAL;
                break;
            case "$":
                storage = MgLogicalDatatype.Storage.DIRECT;
                requirement = MgLogicalDatatype.Requirement.MANDATORY;
                break;
            case "$?":
                storage = MgLogicalDatatype.Storage.DIRECT;
                requirement = MgLogicalDatatype.Requirement.OPTIONAL;
                break;
            default:
                throw new LanguageException("Expected '&', '&?', '$', '$?'. Got '" + operator.getText() + "'.");
        }

        variable = new MgLogicalVariable(
            objectName.getText(),
            new MgLogicalDatatype(typeName.getText(), storage, requirement)
        );
    }
}
