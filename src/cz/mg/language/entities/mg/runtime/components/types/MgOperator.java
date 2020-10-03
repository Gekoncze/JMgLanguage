package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.parts.MgOperatorInfo;


public class MgOperator extends MgFunction {
    @Mandatory @Part
    private final MgOperatorInfo info;

    public MgOperator(ReadableText name, MgOperatorInfo info) {
        super(name);
        this.info = info;
    }

    public MgOperatorInfo getInfo() {
        return info;
    }
}
