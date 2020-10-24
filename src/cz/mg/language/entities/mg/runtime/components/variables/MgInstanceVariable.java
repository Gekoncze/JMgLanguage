package cz.mg.language.entities.mg.runtime.components.variables;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.language.annotations.task.Cache;


public abstract class MgInstanceVariable extends MgVariable {
    @Mandatory @Cache
    private int offset;

    public MgInstanceVariable(ReadableText name) {
        super(name);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
