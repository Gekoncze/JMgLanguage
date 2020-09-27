package cz.mg.language.entities.mg.runtime.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.MgVariable;


public class MgMemberVariable extends MgVariable implements MgPart {
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
