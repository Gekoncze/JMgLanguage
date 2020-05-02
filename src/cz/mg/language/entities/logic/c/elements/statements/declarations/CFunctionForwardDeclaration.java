package cz.mg.language.entities.logic.c.elements.statements.declarations;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.c.parts.CFunctionType;
import cz.mg.language.entities.logic.c.parts.CType;
import cz.mg.language.entities.logic.c.parts.CVariable;


public class CFunctionForwardDeclaration extends CForwardDeclaration implements Named {
    @Value
    private final ReadableText name;

    @Part
    private final CFunctionType type;

    public CFunctionForwardDeclaration(ReadableText name) {
        this.name = name;
        this.type = new CFunctionType();
    }

    public CFunctionForwardDeclaration(ReadableText name, CFunctionType type) {
        this.name = name;
        this.type = type;
    }

    public CFunctionType getType() {
        return type;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public List<CVariable> getInput() {
        return type.getInput();
    }

    public CType getOutput() {
        return type.getOutput();
    }

    public void setOutput(CType output) {
        this.type.setOutput(output);
    }
}
