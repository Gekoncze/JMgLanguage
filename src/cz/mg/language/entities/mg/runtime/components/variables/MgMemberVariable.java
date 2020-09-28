package cz.mg.language.entities.mg.runtime.components.variables;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public abstract class MgMemberVariable extends MgVariable {
    @Mandatory @Cache
    private int offset;

    public MgMemberVariable(ReadableText name) {
        super(name);
    }

    public MgMemberVariable(ReadableText name, MgDatatype datatype) {
        super(name, datatype);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
