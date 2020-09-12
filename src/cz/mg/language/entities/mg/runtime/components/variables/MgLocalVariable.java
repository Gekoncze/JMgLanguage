package cz.mg.language.entities.mg.runtime.components.variables;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public class MgLocalVariable extends MgVariable {
    private static final MgType TYPE = new MgType("LocalVariable");

    @Mandatory @Cache
    private int offset;

    protected MgLocalVariable(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgLocalVariable(ReadableText name) {
        super(TYPE, name);
        this.offset = offset;
    }

    public MgLocalVariable(ReadableText name, MgDatatype datatype) {
        super(TYPE, name);
        this.offset = offset;
        setDatatype(datatype);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
